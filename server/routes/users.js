module.exports = function(express, userController) {
    const router = express.Router();
    router.get('/', function(req, res) {
      res.send('respond with a resource');
    });

    router.get('/oauth2-url', (req, res) => {
        userController.getUrl((url) => {
            res.json({url: url});
        });
    });

    router.get('/oauth2-tokens/:code', (req, res) => {
        let code = req.params.code + '#';
        userController.getAccessToken(code, (err, response) => {
            if(err) throw err;
            res.json(response);
        });
    });
   
    router.post('/Auth-android', (req, res) => {  
    console.log("idToken " + req.body.idToken);
    console.log("\n email : " + req.body.email);
    userController.androidAuthentification(req.body.email, (err,response) =>{
            if(err) throw err;
            res.json(response);
        });
    });
    
   return router;
}
