module.exports = function(express, rdvController) {

   const router = express.Router();
   
    // Add Rendez-Vous
    router.post('/rdv', (req, res) => {
        rdvController.addRdv(req.body, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    
    // delete Rendez-Vous    
    router.delete('/:id', (req, res) => {
        rdvController.deleteRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    
    // get Rendez-Vous By Student
    router.get('/student/:id', (req, res) => {
        rdvController.getRdvByStudent(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    
    // get Rendez-Vous By Teacher
    router.get('/teacher/:id', (req, res) => {
        rdvController.getRdvByTeacher(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    
    // Get all rendez-Vous
    router.get('/', (req, res) => {
        rdvController.getAllRdv( (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    
   return router;
}
