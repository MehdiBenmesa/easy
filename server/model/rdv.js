module.exports = function(mongoose){
        var rdvSchema = mongoose.Schema({
                        reason : String,
                        stateTeacher : String,
                        stateStudent : String,
                        teacher : {type : mongoose.Schema.Types.ObjectId, ref: 'Teacher'},
                        student : {type : mongoose.Schema.Types.ObjectId, ref: 'Student'},
                        date : {type: Date, default: Date.now}
                    } , {collection : 'rdv'});
    return mongoose.model('Rdv', rdvSchema);
}

//58e3cb7bf36d283c9c874926 Salah
//59074db51e02411bf5824574 Bousbia