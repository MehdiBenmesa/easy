

module.exports = function(express, notesController) {



   const router = express.Router();
    router.post('/note', (req, res) => {
        notesController.addNote(req.body , (err, note) => {
            if(err) throw err;
            res.json(note);
        });
    });

    router.post('/notes', (req, res) => {
        notesController.addNotes(req.body , (err, note) => {
            if(err) throw err;
            res.json(note);
        });
    });

    router.get('/note-by-student/:student',(req, res) => {
        notesController.getNoteByStudent(req.params.student, (err, note) => {
            if(err) throw err;
            res.json(note);
        });
    });

    router.get('/note-by-student/:student',(req, res) => {
        notesController.getNoteByStudent(req.params.student, (err, note) => {
            if(err) throw err;
            res.json(note);
        });
    });
    router.get('/student/:student/module/:module',(req, res) => {
        notesController.getNoteByModules(req.params.student,req.params.module, (err, note) => {
            if(err) throw err;
            res.json(note);
        });
    });
    router.get('/', (req, res) => {
        notesController.getAllNotes( (err, note) => {
            if(err) throw err;
            res.json(note);
        });
    });
   return router;
}
