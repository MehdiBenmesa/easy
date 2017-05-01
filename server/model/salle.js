module.exports = function(mongoose){
  var Schema = mongoose.Schema;
  var salleSchema = new Schema({
    name : String,
    type : String,
    capacite : Number
  });
  return mongoose.model('Salle', salleSchema);
};
