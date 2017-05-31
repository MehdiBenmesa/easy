module.exports = function(express, rdvController) {

   const router = express.Router();
   
    // Add Rendez-Vous
    router.post('/', (req, res) => {
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

    router.post('/refuse/:id', (req, res) => {
        rdvController.refuseRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });

    router.post('/accept/:id', (req, res) => {
        rdvController.acceptRdv(req.body,req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });

    router.get('/delete-student/:id', (req, res) => {
        rdvController.deleteRdvStudent(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });

    router.get('/delete-teacher/:id', (req, res) => {
        rdvController.deleteRdvTeacher(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });

    // les rendez-vous acceptée d'un étudiant 
    router.get('/student/accepted/:id', (req, res) => {
        rdvController.getAcceptedStudentRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    // les rendez-vous acceptée d'un enseignant 
    router.get('/teacher/accepted/:id', (req, res) => {
        rdvController.getAcceptedTeacherRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    
    
    // les rendez-vous refusée d'un étudiant 
    router.get('/student/refused/:id', (req, res) => {
        rdvController.getRefusedStudentRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });

    // les rendez-vous réfusée d'un enseignant 
    router.get('/teacher/refused/:id', (req, res) => {
        rdvController.getRefusedTeacherRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });

    
    // les rendez-vous En Attnt d'un étudiant 
    router.get('/student/enattent/:id', (req, res) => {
        rdvController.getEnAttentStudentRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    
    // les rendez-vous En Attnt d'un Enseignant
    router.get('/teacher/enattent/:id', (req, res) => {
        rdvController.getEnAttentTeacherRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });

    // les rendez-vous En Attnt d'un étudiant 
    router.get('/student/effectuer/:id', (req, res) => {
        rdvController.getEffectuerStudentRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });
    
    // les rendez-vous En Attnt d'un Enseignant
    router.get('/teacher/effectuer/:id', (req, res) => {
        rdvController.getEffectuerTeacherRdv(req.params.id, (err, rdv) => {
            if(err) throw err;
            res.json(rdv);
        });
    });

    
   return router;
}
