module.exports = function(mongoose){
  var Schema = mongoose.Schema;
  var seanceSchema = new Schema({
    module : {type : mongoose.Schema.Types.ObjectId, ref: 'Module'},
    salle : {type : mongoose.Schema.Types.ObjectId, ref: 'Salle'},
    teacher: {type : mongoose.Schema.Types.ObjectId, ref: 'Teacher'},
    type: String,
    starts: String,
    ends: String
  });

  var autoPopulateSeance= function(next) {
      this.populate('module salle teacher');
      next();
  };

  seanceSchema.pre('find', autoPopulateSeance);
  seanceSchema.pre('findOne', autoPopulateSeance);

  return mongoose.model('Seance', seanceSchema);
};
