
module.exports = function(mongoose){
      var absenceSchema = mongoose.Schema({
                        date : String,
                        students : [{type : mongoose.Schema.Types.ObjectId, ref: 'Student'}],
                        seance : {type : mongoose.Schema.Types.ObjectId, ref: 'Seance'} // ** !!
                    } , {collection : 'absence'});
    return mongoose.model('Absence', absenceSchema);
}
