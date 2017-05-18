module.exports = function(Student, Teacher, Rdv){

    function addRdv(obj,callback){
        //TODO
       let rdv = new Rdv(obj);
        rdv.save((err, rdv) => {
            callback(err, rdv);
        });
    }
    
     function deleteRdv(rdvId, callback){
        Rdv.findByIdAndRemove(rdvId, (err, rdv) => {
             callback(err, {message: 'success'})
    });
    }
    
    
    function getRdvByStudent(studentId,callback){
        //TODO  
        Rdv.find({student : studentId}).populate('teacher').exec( (err, rdv) => {
            callback(err, rdv);
        });
    }
    
    
    function getRdvByTeacher(techerId,callback){
        //TODO  
        Rdv.find({teacher : techerId}).populate('student').exec( (err, rdv) => {
            callback(err, rdv);
        });
    }
    
    function getAllRdv(callback){
        //TODO  
        Rdv.find({},(err, rdv) => {
            callback(err, rdv);
        });
    }

    function refuseRdv(rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId, {$push :{ state : "refused"}}, (err, rdv) => {
                    callback(err, rdv);
        }); 
    }
    function acceptRdv(rdvId, callback){
        Rdv.findByIdAndUpdate(rdvId, {$push :{ state : "accepted"}}, (err, rdv) => {
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
      acceptRdv
    };
}
