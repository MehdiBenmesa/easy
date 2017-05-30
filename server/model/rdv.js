module.exports = function(mongoose){
        var rdvSchema = mongoose.Schema({
                        reason : String,
                        state : { type : String, default: "enattent" },
                        supTeacher : { type : Boolean, default: false },
                        supStudent : { type : Boolean, default: false },
                        teacher : {type : mongoose.Schema.Types.ObjectId, ref: 'Teacher'},
                        student : {type : mongoose.Schema.Types.ObjectId, ref: 'Student'},
                        date : String,
                        heur : String,
                        remarque : String
                    } , {collection : 'rdv'});
    return mongoose.model('Rdv', rdvSchema);
}