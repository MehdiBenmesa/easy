module.exports = function (Student, Seance, Absence) {

    function addAbsence(obj, callback) { 
        console.log(obj);
        //let abs = JSON.parse(obj.seance);
        let absence = new Absence(obj);
        absence.save((err, absence) => {
          Absence.populate(absence, 'seance', (err, mabsence) => {
            callback(err, mabsence);
          });
        });
        // let EnsStudent
        // for(var i=0;i<obj.students.length;i++)
        // Student.findOne({_id : obj.students.}, (err, user) => {
        //           console.log(user);
        //           NotificationController.sendNotification(user , "Ajouter Note avec Succes" , (err, notification) => {
        //               callback(err, notification);
        //           } );
        //           callback(err, user);
        // });
    }
// update Seance 
    function updateAbsence(obj, callback) {   
        console.log(obj);
        var abs = JSON.parse(obj.absence);
        console.log(abs);
        Absence.findByIdAndUpdate(abs.idabs,
                    {students : abs.students}, (err,res) => {
                callback(err,res);   
        }); 
    }

    function getAbsenceByStudent(studentId, callback) {
        Absence.find({ 'students': studentId }).populate('seance').exec((err, res) => {
            callback(err, res);
        });
    }

    function getAbsenceByModules(studentId, moduleId, callback) {
        Absence.find({ 'students': studentId }).populate('seance').exec((err, res) => {
            let absences = [];
            for (var i = 0; i < res.length; i++) {
                let absence = res[i].seance.module.id;
                if (absence == moduleId) {
                    absences.push(res[i]);
                }
            }
            let absencesRes = removeDuplicates(absences);
            callback(err, absencesRes);
        });
    }

    function removeDuplicates(arrayIn) {
        var arrayOut = [];
        for (var a = 0; a < arrayIn.length; a++) {
            if (arrayOut[arrayOut.length - 1] != arrayIn[a]) {
                arrayOut.push(arrayIn[a]);
            }
        }
        return arrayOut;
    }

    function getAbsenceBySeance(seanceId, callback){
        Absence.find({'seance': seanceId }).exec( (err, res) => {
                        callback(err, res);
        });
    }


    function getAllAbsences(callback) {
        Absence.find({}).populate('seance').exec((err, absences) => {
            callback(err, absences);
        });

    }

    function deleteAbsence(absenceId, callback) {
        Absence.findByIdAndRemove(absenceId, (err, module) => {
            callback(err, { message: 'success' })
        });
    }

    function getAbsencesTeacher(teacher, callback) {
        Absence.find({}).populate({
            path: 'seance',
            match: { 'teacher': teacher }
        }).exec((err, result) => {
            let absences = [];
            result.forEach(absence => {
                if (absence.seance != null) {
                    absences.push(absence);
                }
            });
            callback(err, absences);
        });
    }

    function getAbsenceBySeanceDate(seanceId,dateinput, callback) {
        Absence.find({ seance: seanceId, date : dateinput }).exec((err, res) => {
            callback(err, res);
        });
    }

    function getAbsenceByModulesDate(studentId, moduleId,dateinput, callback) {
        Absence.find({ 'students': studentId, 'date': dateinput }).populate('seance').exec((err, res) => {
            let absences = [];
            for (var i = 0; i < res.length; i++) {
                let absence = res[i].seance.module.id;
                if (absence == moduleId) {
                    absences.push(res[i]);
                }
            }
            let absencesRes = removeDuplicates(absences);
            callback(err, absencesRes);
        });
    }

    return {
        getAbsenceByStudent,
        getAllAbsences,
        getAbsenceBySeance,
        addAbsence,
        deleteAbsence,
        getAbsenceByModules,
        getAbsencesTeacher,
        getAbsenceBySeanceDate,
        getAbsenceByModulesDate,
        updateAbsence
    };
}
