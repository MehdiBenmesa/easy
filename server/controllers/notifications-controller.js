module.exports = function(gcm , notification ){

    function sendNotification(user  , messageToSend , callback){
        //TODO
          var message = new gcm.Message({data: {message: messageToSend}});
          var regTokens = [user.gcmtoken];
          var sender = new gcm.Sender(notification.gcm_api_key);
          sender.send(message, { registrationTokens: regTokens }, function (err, response) {

            if (err){

              console.error(err);
              callback(err , notification.error.msg_send_failure);

            } else  {

              console.log(response);
              callback(err , notification.success.msg_send_success);
            }

          });
    }

    return {
      sendNotification
    };
}
