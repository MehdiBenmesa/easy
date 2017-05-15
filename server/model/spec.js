module.exports = function(mongoose){
        var specSchema = mongoose.Schema({
          name : String,
          year : String,
          semestre : Number,
          courses : [{
            course : {type : mongoose.Schema.Types.ObjectId},
            teacher : {type : mongoose.Schema.Types.ObjectId, ref: 'Teacher' }
          }],
          sections : [{
            sectionName : String,
            groupes : [{
              groupeName : String,
              emploi : {
                sunday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
                monday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
                tuesday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
                wednesday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}],
                thursday: [{type : mongoose.Schema.Types.ObjectId, ref : 'Seance'}]
              },
              students : [{
                type: mongoose.Schema.Types.ObjectId,
                ref:'Student'
              }]
            }]
          }]
          } , {collection : 'spec'});
    return mongoose.model('Spec', specSchema);
  }
