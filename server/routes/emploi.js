
module.exports = function(express, emploiController) {

   const router = express.Router();
  // L'ajout d'une nouvelle Seance
  router.post('/seance', (req, res) => {
    emploiController.addSeance(req.body.groupeId, req.body.sectionId, req.body.day, req.body.seance, (err, result) => {
      if(err) throw err;
      res.json(result);
    });
  });


  router.get('/teacher/:id', (req, res) => {
    emploiController.getTeacherEmploie(req.params.id, (err, result) => {
      if(err) throw err;
      res.json(result);
    });
  });

  router.get('/teacher/:id/:day', (req, res) => {
    emploiController.getTeacherEmploieDay(req.params.id,req.params.day, (err, result) => {
      if(err) throw err;
      res.json(result);
    });
  });

  // Récupération de l'emploi du temps
  router.get('/:section/:groupe', (req, res) => {
    emploiController.getTimeTable(req.params.section, req.params.groupe, (err, result) => {
      if(err) throw err;
      res.json(result);
    });
  });


  router.get('/:groupe', (req, res) => {
    emploiController.getTimeTableByGroupe(req.params.groupe, (err, result) => {
      if(err) throw err;
      res.json(result);
    });
  });

router.get('/seances', (req, res) => {
    emploiController.getAllSeances( (err, result) => {
      if(err) throw err;
      res.json(result);
    });
  });

   router.get('/salle/:id', (req, res) => {
      emploiController.getSalleEmploie(req.params.id , (error, salle) => {
        if(error) throw error;
        res.json(salle);
      });
    });

  // La suppression d'une seance donnée
  router.post('/delete-seance', (req, res) => {
    emploiController.deleteSeance(
      req.body.sectionId,
      req.body.groupeId,
      req.body.seanceId,
      req.body.teacherId,
      req.body.salleId,
      req.body.day,
      (err, result) => {
        if(err) throw err;
        res.json(result);
      });
  });

  router.get('/teacher-date/:teacher/:date', (req, res) => {
      emploiController.getTeacherEmploieDate(req.params.id, (err, result) => {
        if(err) throw err;
        res.json(result);
      });
  });



   return router;
}
