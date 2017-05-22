module.exports = function(express, absenceController) {

   const router = express.Router();

    router.post('/', (req, res) => {
        absenceController.addAbsence(req.body, (err, absence) => {
            if(err) throw err;
            res.json(absence);
        });
    });

    router.delete('/:id', (req, res) => {
        absenceController.deleteAbsence(req.params.id, (err) => {
            if(err) throw err;
            res.send({message : 'absence deleted '});
        });
    });

    router.get('/student/:student',(req, res) => {
        absenceController.getAbsenceByStudent(req.params.student, (err, absence) => {
            if(err) throw err;
            res.json(absence);
        });
    });
    router.get('/',(req, res) => {
        absenceController.getAllAbsences( (err, absence) => {
            if(err) throw err;
            res.json(absence);
        });
    });

    router.get('/seance/:seance',(req, res) => {
        absenceController.getAbsenceBySeance(req.params.seance, (err, absence) => {
            if(err) throw err;
            res.json(absence);
        });
    });

    router.get('/student/:student/:module',(req, res) => {
        absenceController.getAbsenceByModules(req.params.student,req.params.module, (err, absence) => {
            if(err) throw err;
            res.json(absence);
        });
    });


    router.get('/seance-date/:seance/:date',(req, res) => {
        absenceController.getAbsenceBySeanceDate(req.params.seance,req.params.date, (err, absence) => {
            if(err) throw err;
            res.json(absence);
        });
    });

    router.get('/student/:student/:module/:date',(req, res) => {
        absenceController.getAbsenceByModulesDate(req.params.student,req.params.module,req.params.date, (err, absence) => {
            if(err) throw err;
            res.json(absence);
        });
    });

  router.get('/teacher/:teacher', (req, res) => {
    absenceController.getAbsencesTeacher(req.params.teacher, (err, absences) => {
      if(err) throw err;
      res.json(absences);
    });
  });


   return router;
}
