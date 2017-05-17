module.exports = function(express, absenceController) {

   const router = express.Router();

    router.post('/absence', (req, res) => {
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
    
    
    // router.get('/absence-by-seance/:seance',(req, res) => {
    //     absenceController.getNoteByStudent(req.params.student, (err, note) => {
    //         if(err) throw err;
    //         res.json(note);
    //     });
    // });
   return router;
}
