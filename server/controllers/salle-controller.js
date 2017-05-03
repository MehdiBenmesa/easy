module.exports = function(Salle){

  function getSalle(_id, callback){
    Salle.findOne({_id : _id}, (error, salle) => {
       callback(error, Salle);
    });
  }

  function getSalles(callback){
    Salle.find({}, (error, salle) => {
      callback(error, salle);
    });
  }

  function saveSalle(p , callback){
    var salle = new Salle(p);
    salle.save((err, salle) => {
      callback(err, salle);
    });
  }

  function deleteSalle(id, callback){
    Salle.findByIdAndRemove(id, (err, module) => {
       callback(err, {message: 'success'});
    });
  }

  function checkSalle(salleId, day,  starts, ends, callback){
    let occupied = false;
    Salle.findOne({_id :salleId}, (err, salle) => {
      salle.populate('emploi.sunday emploi.monday emploi.tuesday emploi.wednesday emploi.thursday', (err, salle) => {
        salle.emploi[day].forEach(seance => {
          console.log(starts  > seance.starts);
          console.log(starts < seance.ends)
          if( (starts >= seance.starts && starts <= seance.ends )||
               (ends >= seance.starts && ends <= seance.ends )){
            occupied = true;
          }
        });
        callback(err, {occupied});

      })
    });
  }

  return {
    getSalle,
    getSalles,
    saveSalle,
    deleteSalle,
    checkSalle
  }
}
