
module.exports = function(express, emploiController) {

   const router = express.Router();
  // L'ajout d'une nouvelle Seance
  router.post('/seance', (req, res) => {
    emploiController.addSeance(req.body.groupeId, req.body.sectionId, req.body.day, req.body.seance, (err, result) => {
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


   return router;
}
