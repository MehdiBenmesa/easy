
module.exports = function(mongoose){
      var groupeSchema = mongoose.Schema({
                        number : Number,
                        students : [{type : mongoose.Schema.Types.ObjectId, ref: 'Student'}],
                        module : [{type : mongoose.Schema.Types.ObjectId, ref: 'Module'}] // ** !! 
                    } , {collection : 'groupe'});
    return mongoose.model('Groupe', groupeSchema);
}

//modules-by-student
