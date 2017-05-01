module.exports = function(mongoose){
        var moduleSchema = mongoose.Schema({
                        name : String,
                        abre : String,
                        description : String,
                        coef : Number,
                        credit : Number,
                        hoursTd : Number,
                        hoursCours : Number
                    } , {collection : 'module'});
    return mongoose.model('Module', moduleSchema);
  }
