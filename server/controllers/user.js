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
          console.log("token = "  + tokens.id_token);
          console.log("access_token = "  + tokens.access_token);
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
             console.log("Not Allowed"); 
           }
          });

        });
    }

    function verifyToken(token){
        oauth2Client.verifyIdToken(
          token,
          googleConfig.CLIENT_ID,
          // Or, if multiple clients access the backend:
          //[CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3],
          function(e, login) {
            //var payload = login.getPayload();
            //var userid = payload['sub'];
            return login.getPayload();
        //callback(e , login));
            // If request specified a G Suite domain:
            //var domain = payload['hd'];
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

     function setOrUpdategmcToken(body,callback) {
       User.findOneAndUpdate({mail : body.email}, {$set:{gcmtoken: body.registrationId }}, {new: true} , (err, user) => {
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
      androidAuthentification , 
      setOrUpdategmcToken
      }
}
