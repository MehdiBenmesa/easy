
module.exports = function(mongoose, extend){
      var userSchema = mongoose.Schema({
                        name: {type : String , required : true} ,
                        lastname: {type : String , required : true} ,
                        sexe : String ,
                        mail: {type : String , required : true} ,
                        address : String ,
                        tell : String,
                    } , {collection : 'user', discriminatorKey : '_type'});

    var studentSchema = userSchema.extend({
                        matricule : String ,
                        groupe : {type : mongoose.Schema.Types.ObjectId},
                        section : {type : mongoose.Schema.Types.ObjectId},
                        notes : [{type : mongoose.Schema.Types.ObjectId, ref: 'Note'}],
                      //  absences : [{type : mongoose.Schema.Types.ObjectId}]
    });

    var managerSchema = userSchema.extend({
        post : String
    });

    var teacherSchema = userSchema.extend({
        modules : [{type : mongoose.Schema.Types.ObjectId, ref : 'Module'}],
        emploi : {
          sunday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
          monday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
          tuesday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
          wednesday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
          thursday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}]
        }
    });

    var User = mongoose.model('User', userSchema);
    var Manager = mongoose.model('Manager', managerSchema);
    var Student = mongoose.model('Student', studentSchema);
    var Teacher = mongoose.model('Teacher', teacherSchema);

    return { User, Manager, Student, Teacher }
}
