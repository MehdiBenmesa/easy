module.exports = function(google, googleConfig, User ){

    var OAuth2Client = google.auth.OAuth2;
    var plus = google.plus('v1');
    var oauth2Client = new OAuth2Client(googleConfig.CLIENT_ID,
                                        googleConfig.CLIENT_SECRET,
                                        googleConfig.REDIRECT_URL);
    function getUrl(callback) {
      // generate consent page url
      var url = oauth2Client.generateAuthUrl({
        access_type: 'offline', // will return a refresh token
        scope: ['https://www.googleapis.com/auth/plus.me'  , 'profile', 'email'] // can be a space-delimited string or an array of scopes
      });
      callback(url)
  }

    function getAccessToken(code, callback){
        oauth2Client.getToken(code, function (err, tokens) {
          if (err) {
            return callback(err);
          }
          oauth2Client.setCredentials(tokens);
          oauth2Client.verifyIdToken(
          tokens.id_token,
          googleConfig.CLIENT_ID,
          function(e, login) {
           let payLoad = login.getPayload();
           if(e) throw e ;
              User.findOne({mail : payLoad.email}, (err, user) => {
                  console.log(user);
                  callback(err, {
                      tokens,
                      user
                  });
              });
           if (login.getPayload().hd != "esi.dz"){
             callback(err, {
               message: "Authorization required"
             });
           }
          });

        });
    }

    function verifyToken(tokenId){
        oauth2Client.verifyIdToken(
          tokenId,
          googleConfig.CLIENT_ID,
          function(e, login) {
            console.log(login);
          });
    }
    
    function androidAuthentification(email,callback) {
       User.findOne({mail : email}, (err, user) => {
                  console.log(user);
                  callback(err, {
                      user
                  });
              });
    }
    return{
      getUrl,
      getAccessToken ,
      verifyToken,
      androidAuthentification
      }
}
