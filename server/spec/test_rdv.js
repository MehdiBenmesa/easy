var request = require("request");
const appConfig = require('../config/app-config.js');
var base_url = "http://localhost:3000";
var mongoose = require("mongoose");
mongoose.connect(appConfig.db);
//const Section = require('../model/section.js')(mongoose);
describe("Server test", function () {

// Ajouter Rendez-Vous 
    describe("Post  rdv/rdv", function () {
        it("Post Rendez-Vous return with code 200", function (done) {
            request.post({
                url : base_url + "/rdv/rdv", 
                form : {
                    student : "58e3cb7bf36d283c9c874926",
                    teacher : "59074db51e02411bf5824574",
                    reason: "Sujet de PFE" 
                }},
                function (error, response, body) {
                    
                expect(response.statusCode).toBe(200);
                
                console.log(" ===============================================================");
                console.log(" =============== TESTED BY BOUKHETTA SALAH EDDINE ==============");
                console.log(" ===============================================================");
                console.log(body);
                console.log(" ===============================================================");
                console.log(" =============== TESTED BY BOUKHETTA SALAH EDDINE ==============");
                console.log(" ===============================================================");
                done();
                });
        });
    });

    // groupes by teacher and module
     describe("GET  /rdv/", function () {
        it("Get Rendez-Vous return with code 200", function (done) {
            request.get(base_url + "/rdv/", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                console.log(" ===============================================================");
                console.log(" =============== TESTED BY BOUKHETTA SALAH EDDINE ==============");
                console.log(" ===============================================================");
                console.log(body);
                console.log(" ===============================================================");
                console.log(" =============== TESTED BY BOUKHETTA SALAH EDDINE ==============");
                console.log(" ===============================================================");
                done();
            });
        });
    });
   
    
});