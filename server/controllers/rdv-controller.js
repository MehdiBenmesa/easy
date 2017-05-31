module.exports = function(Student, Teacher, Rdv,NotificationController){


 // Ajouter Rendez-Vous 
    function addRdv(obj,callback){
        //TODO
       let rdv = new Rdv(obj);
        rdv.save((err, rdv) => {
            callback(err, rdv);
        });
        Teacher.findOne({_id : obj.teacher}, (err, user) => {
                  console.log(user);
                  NotificationController.sendNotification(user , "vous avez un rendez-vous", (err, notification) => {
                      callback(err, notification);
                  } );
                  callback(err, user);
              });
    }
    
    // Accepter Rendez-Vous 
    function acceptRdv(obj,rdvId, callback){
        console.log(obj);
        Rdv.findByIdAndUpdate(rdvId, 
        {$set :{ state : "accepted", date : obj.date, heur : obj.heur, remarque : obj.remarque}},
         (err, rdv) => {
             Student.findOne({_id : rdv.student}, (err, user) => {
                  console.log(user);
                  NotificationController.sendNotification(user , "Votre rendez-vous a été acceptée", (err, notification) => {
                      callback(err, notification);
                  } );
                  callback(err, user);
              });
                    callback(err, rdv);
        });
        
    }

    // Supprimer Rendez-Vous 
     function deleteRdv(rdvId, callback){
        Rdv.findByIdAndRemove(rdvId, (err, rdv) => {
             callback(err, {message: 'success'})
    });
    }
    
   // get Rendez-Vous par Etudiant  
    function getRdvByStudent(studentId,callback){
        //TODO  
        Rdv.find({student : studentId}).populate('teacher').exec( (err, rdv) => {
            callback(err, rdv);
        });
    }
    
    // get Rendez-Vous par Enseignant 
    function getRdvByTeacher(techerId,callback){
        //TODO  
        Rdv.find({teacher : techerId}).populate('student').exec( (err, rdv) => {
            callback(err, rdv);
        });
    }
    
    // All Rendez-Vous 
    function getAllRdv(callback){
        //TODO  
        Rdv.find({},(err, rdv) => {
            callback(err, rdv);
        });
    }

    // Refuser Rendez-Vous 
    function refuseRdv(rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId, {$set :{ state : "refused"}}, (err, rdv) => {
            Student.findOne({_id : rdv.student}, (err, user) => {
                  console.log(user);
                  NotificationController.sendNotification(user , "Votre rendez-vous a été réfusée", (err, notification) => {
                      callback(err, notification);
                  } );
                  callback(err, user);
              });
                    callback(err, rdv);
        }); 
        /*
        Student.findOne({_id : obj.student}, (err, user) => {
                  console.log(user);
                  NotificationController.sendNotification(user , "Votre rendez-vous a été réfusée", (err, notification) => {
                      callback(err, notification);
                  } );
                  callback(err, user);
              });*/
    }

    // Supprimer Rendez-Vous Etudiant 
    function deleteRdvStudent(rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId, {$set :{ supStudent : true }}, (err, rdv) => {
                    callback(err, rdv);
        });
    }

    // Supprimer Rendez-Vous Enseignant 
    function deleteRdvTeacher(rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId, {$set :{ supTeacher : true }}, (err, rdv) => {
                    callback(err, rdv);
        });
    }

    // les rendez-vous acceptée d'un étudiant 
    function getAcceptedStudentRdv(rdvId, callback){
            Rdv.find({student : rdvId,state : "accepted" }).populate('teacher').exec((err, rdv) => {
                callback(err, rdv);
            });
        }
    
    // les rendez-vous acceptée d'un enseignant 
    function getAcceptedTeacherRdv(rdvId, callback){
            Rdv.find({teacher : rdvId,state : "accepted"}).populate('student').exec( (err, rdv) => {
                    callback(err, rdv);
            });
        }
    
    // les rendez-vous refusée d'un étudiant 
    function getRefusedStudentRdv(rdvId, callback){
            Rdv.find({student : rdvId,state : "refused"}).populate('teacher').exec( (err, rdv) => {
                    callback(err, rdv);
            });
        }

    // les rendez-vous réfusée d'un enseignant 
    function getRefusedTeacherRdv(rdvId, callback){
            Rdv.find({teacher : rdvId,state : "refused"}).populate('student').exec( (err, rdv) => {
                    callback(err, rdv);
            });
        }

    // les rendez-vous En Attnt d'un étudiant 
    function getEnAttentStudentRdv(rdvId, callback){
            Rdv.find({student : rdvId, state : "enattent" }).populate('teacher').exec( (err, rdv) => {
                    callback(err, rdv);
            });
    }

    // les rendez-vous En Attnt d'un Enseignant
    function getEnAttentTeacherRdv(rdvId, callback){
            Rdv.find({teacher : rdvId,state : "enattent"}).populate('student').exec( (err, rdv) => {
                    callback(err, rdv);
            });
    }
    
    // les rendez-vous En Attnt d'un étudiant 
    function getEffectuerStudentRdv(rdvId, callback){
            Rdv.find({student : rdvId, state : "effectuer" }).populate('teacher').exec( (err, rdv) => {
                    callback(err, rdv);
            });
    }

    // les rendez-vous En Attnt d'un Enseignant
    function getEffectuerTeacherRdv(rdvId, callback){
            Rdv.find({teacher : rdvId,state : "effectuer"}).populate('student').exec( (err, rdv) => {
                    callback(err, rdv);
            });
    }




    return {
      addRdv,
      deleteRdv,
      getRdvByStudent,
      getRdvByTeacher,
      getAllRdv,
      refuseRdv,
      acceptRdv,
      deleteRdvTeacher,
      deleteRdvStudent,
      getAcceptedStudentRdv,
      getAcceptedTeacherRdv,
      getRefusedStudentRdv,
      getRefusedTeacherRdv,
      getEnAttentStudentRdv,
      getEnAttentTeacherRdv,
      getEffectuerStudentRdv,
      getEffectuerTeacherRdv,     
    };
}
