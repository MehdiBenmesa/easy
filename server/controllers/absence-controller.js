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
                     //   let student = res.students;
                        //console.log(res.students);
                        callback(err, res);
                    });
        }

    function getAbsenceBySeance(seanceId, callback){
        Absence.find({ 'seance': seanceId }, (err, res) => {
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

    function getAbsencesTeacher(teacher, callback) {
      Absence.find({}).populate({
        path : 'seance',
        match : {'teacher' :teacher }
      }).exec( (err, result) => {
        let absences = [];
        result.forEach(absence => {
          if(absence.seance != null){
            absences.push(absence);
          }
        });
        callback(err, absences);
      } );
    }

    return {
      getAbsenceByStudent,
      getAllAbsences,
      getAbsenceBySeance,
      addAbsence,
      getAbsencesTeacher
    };
}
