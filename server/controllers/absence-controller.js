module.exports = function(Student, Seance, Absence){

   function addAbsence(obj, callback){
        //TODO
       let absence = new Absence(obj);
        absence.save((err, absence) => {
            callback(err, absence);
        });
    }
    
    function getAbsenceByStudent(studentId, callback){
        Absence.find({'students' : studentId}).populate('seance').exec(  (err, res) => {
                      //  let absence = res.seance.id("idmodule").module;
                        //console.log(res.students);
                        callback(err, res);
                    });
    }
    
    function getAbsenceByModules(studentId,moduleId,callback){  
        Absence.find({'students' : studentId}).populate('seance').exec(  (err, res) => {
            let absences = [];
            for(var i=0;i< res.length;i++){
                let absence = res[i].seance.module.id;    
                if(absence==moduleId){
                    absences.push(res[i]);
                }
            }
            let absencesRes = removeDuplicates(absences);
            callback(err, absencesRes);
            });
    }
    
    function removeDuplicates(arrayIn) {
    var arrayOut = [];
    for (var a=0; a < arrayIn.length; a++) {
        if (arrayOut[arrayOut.length-1] != arrayIn[a]) {
            arrayOut.push(arrayIn[a]);
        }
    }
    return arrayOut;
    }
    
    function getAbsenceBySeance(seanceId, callback){
        Absence.find({ seance : seanceId }).exec( (err, res) => {
                     //   let student = res.students;
                        //console.log(res.students);
                        callback(err, res);
                    });
    }
    
    
    function getAllAbsences(callback){
        Absence.find({}).exec((err,absences) =>{
            callback(err,absences);
        });
        
    }
    
    function deleteAbsence(absenceId, callback){
        Absence.findByIdAndRemove(absenceId, (err, module) => {
             callback(err, {message: 'success'})
    });
    }
    return {
      getAbsenceByStudent,
      getAllAbsences,
      getAbsenceBySeance,
      addAbsence,
      getAbsenceByModules
    };
}
