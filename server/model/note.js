module.exports = function(mongoose){
        var noteSchema = mongoose.Schema({
                        reason : String,
                        value : Number,
                        module : {type : mongoose.Schema.Types.ObjectId},
                        student : {type : mongoose.Schema.Types.ObjectId},
                        date : {type: Date, default: Date.now}
                    } , {collection : 'note'});
                    
    return mongoose.model('Note', noteSchema);
}

