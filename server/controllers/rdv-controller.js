module.exports = function(Student, Teacher, Rdv){


 // Ajouter Rendez-Vous
    function addRdv(obj,callback){
        //TODO
       let rdv = new Rdv(obj);
        rdv.save((err, rdv) => {
            callback(err, rdv);
        });
    }

    // Accepter Rendez-Vous
    function acceptRdv(obj,rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId,
        {$set :{ state : "accepted", date : obj.date, heur : obj.heur, remarque : obj.remarque}},{new: true},
         (err, rdv) => {
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
    function getRdvByTeacher(teacherId, callback){
        //TODO
        Rdv.find({teacher : teacherId}).populate('student').exec( (err, rdv) => {
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
        Rdv.findByIdAndUpdate(rdvId, {$set :{ state : "refused"}},{new:true}, (err, rdv) => {
                    callback(err, rdv);
        });
    }

  function doneRdv(rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId, {$set :{ state : "done"}},{new:true}, (err, rdv) => {
                    callback(err, rdv);
        });

  }



    // Supprimer Rendez-Vous Etudiant
    function deleteRdvStudent(rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId, {$set :{ supStudent : true }},{new :true}, (err, rdv) => {
                    callback(err, rdv);
        });
    }

    // Supprimer Rendez-Vous Enseignant
    function deleteRdvTeacher(rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId, {$set :{ supTeacher : true }},{new :true}, (err, rdv) => {
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
      doneRdv
    };
}
