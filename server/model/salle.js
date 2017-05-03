module.exports = function(mongoose){
  var Schema = mongoose.Schema;
  var salleSchema = new Schema({
    name : String,
    type : String,
    capacite : Number,
    emploi : {
      sunday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
      monday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
      tuesday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
      wednesday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
      thursday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}]
    }
  });
  return mongoose.model('Salle', salleSchema);
};
