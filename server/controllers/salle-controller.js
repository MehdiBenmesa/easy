module.exports = function(Salle){

  function getSalle(_id, callback){
    Salle.findOne({_id : _id}, (error, salle) => {
       callback(error, salle);
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
  return {
    getSalle,
    getSalles,
    saveSalle,
    deleteSalle
  }
}
