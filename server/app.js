var express = require('express');
var path = require('path');
var favicon = require('static-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');

var bodyParser = require('body-parser');
const google = require('googleapis');
const mongoose = require('mongoose');
const extend = require('mongoose-schema-extend');
var app = express();

app.set('views' , path.join(__dirname , 'views'));
app.set("view engine" , "ejs");
app.engine("html" , require("ejs").renderFile);

app.use(favicon());
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(function(req, res, next) {
       res.header("Access-Control-Allow-Origin", "*");
        res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        res.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
        next();
});


const googleConfig = require('./config/google-config.js');
const appConfig = require('./config/app-config.js');

mongoose.Promise = global.Promise;
mongoose.connect(appConfig.db);


/*
let spec = new Spec({
        name: "2CSSIQ",
          year: "2016/2017",
          semestre: 1,
          sections: [{
                sectionName : "Section A",
                groupes : [
                {
                      "groupeName" : "Groupe 1"
                },{
                      "groupeName" : "Groupe 2"
                }]
      }]
});

spec.save((err, spec) => {
  console.log(spec);
});
*/
/*
let abada = new Users.Student({
                        name: "Sadek Rayane"  ,
                        lastname: "AKTOUCHE",
                        sexe : "M",
                        mail: "ds_aktouche@esi.dz" ,
                        address : "Alger",
                        tell : "0556686535",
                        matricule : "13/0161",
                        groupe : "58fd58577d490c1ddafde29e"
})
abada.save((abada) => {
  console.log(abada);
});
let fella = new Users.Student({
                        name: "Fella"  ,
                        lastname: "TATA",
                        sexe : "F",
                        mail: "df_tata@esi.dz" ,
                        address : "Alger",
                        tell : "0559696535",
                        matricule : "13/0077",
                        groupe : "58fd58577d490c1ddafde29e"
})
fella.save();
let imane = new Users.Student({
                        name: "Imene"  ,
                        lastname: "GHASSOUL",
                        sexe : "F",
                        mail: "di_ghassoul@esi.dz" ,
                        address : "Mascara",
                        tell : "0569696535",
                        matricule : "13/0023",
                        groupe : "58fd58577d490c1ddafde29e"
})
imane.save();
let anous = new Users.Student({
                        name: "Selma"  ,
                        lastname: "AKLIL",
                        sexe : "F",
                        mail: "cs_aklil@esi.dz" ,
                        address : "Alger",
                        tell : "0664478024",
                        matricule : "12/0089",
                        groupe : "58ffd8f761150b1ca74a2ace"
})
anous.save();
let besma = new Users.Student({
                        name: "Mohammed"  ,
                        lastname: "LAHIOUEL",
                        sexe : "F",
                        mail: "dm_lahiouel@esi.dz" ,
                        address : "HilioPolis Guelma",
                        tell : "0562566969",
                        matricule : "13/0024",
                        groupe : "58ffd8f761150b1ca74a2ace"
})
besma.save((besma) => {
  console.log(besma);
});
*/

// Models 
const Seance = require('./model/seance.js')(mongoose);
const Users = require('./model/user.js')(mongoose, extend);
const Spec = require('./model/spec.js')(mongoose);
const Groupe = require('./model/groupe.js')(mongoose);
const Module = require('./model/module.js')(mongoose);
const Note = require('./model/note.js')(mongoose);
const Salle = require('./model/salle.js')(mongoose);
const Absence = require('./model/absence.js')(mongoose);
const Rdv = require('./model/rdv.js')(mongoose);
// Controllers 
const notesController = require('./controllers/notes-controller.js')(Users.Student, Note);
const scolariteController = require('./controllers/scolarite-controller.js')(Users.Student, Users.Manager, Users.Teacher,  Spec, Module,Groupe);
const emploiController = require('./controllers/emploi-controller.js')(Spec, Seance,Users.Teacher,Salle);
const salleController = require('./controllers/salle-controller.js')(Salle);
const userController = require('./controllers/user.js')(google, googleConfig, Users.User );
const absenceController = require('./controllers/absence-controller.js')(Users.Student,Seance,Absence);
const rdvController = require('./controllers/rdv-Controller.js')(Users.Student,Users.Teacher,Rdv);
// Routes 
const emploiRoute = require('./routes/emploi.js')(express, emploiController);
const scolariteRoute = require('./routes/scolarite.js')(express, scolariteController);
const notesRoute = require('./routes/notes.js')(express, notesController);
const salleRoute = require('./routes/salle.js')(express, salleController);
const users = require('./routes/users')(express, userController);
const absenceRoute = require('./routes/absence.js')(express, absenceController);
const rdvRouter = require('./routes/rdv.js')(express,rdvController);

app.use('/users', users);
app.use('/scolarite', scolariteRoute);
app.use('/notes', notesRoute);
app.use('/salles', salleRoute);
app.use('/emploi', emploiRoute);
app.use('/absences', absenceRoute);
app.use('/rdv',rdvRouter);

app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

if (app.get('env') === 'development') {
    app.use(function(err, req, res, next) {
        res.status(err.status || 500);
        res.json({
            message: err.message,
            error: err
        });
    });
}

app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.json({
        message: err.message,
        error: {}
    });
});

module.exports = app;
