module.exports = function(express, salleController){
  var router = express.Router();

  router.get('/', (req, res) => {
    salleController.getSalles((error, salles) => {
       if(error) throw error;
       res.json(salles);
    });
  });

  router.get('/:id', (req, res) => {
    salleController.getSalle(req.params.id , (error, salle) => {
      if(error) throw error;
      res.json(salle);
    });
  });

  router.post('/', (req, res) => {
    salleController.saveSalle(req.body , (error, salle) => {
      if(error) throw error;
      res.json(salle);
    })
  });

  router.delete('/:id', (req, res) => {
      salleController.deleteSalle(req.params.id, (error) => {
        if(error) throw error;
        res.send({message : 'done'});
      });
    });
  return router;
}
