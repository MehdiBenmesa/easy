var request = require("request");
const appConfig = require('../config/app-config.js');
var base_url = "http://localhost:3000";
var mongoose = require("mongoose");
mongoose.connect(appConfig.db);
const Section = require('../model/section.js')(mongoose);
describe("Server test", function() {

describe("GET /", function() {
      it("return with code 200" , function (){
          request.get(base_url  + "/", function(error , response , body){
                expect(response.statusCode).toBe(200);
          });
      });
  });


describe("GET /scolarite/get-sections", function() {
      it("Get All Sections return with code 200" , function (done){
          request.get(base_url + "/scolarite/get-sections" , function(error , response , body){
                expect(response.statusCode).toBe(200);
                done();
          });
      });
  });


describe("GET /scolarite/get-groupes", function() {
      it("Get All Groups return with code 200" , function (done){
          request.get(base_url  + "/scolarite/get-groupes", function(error , response , body){
                expect(response.statusCode).toBe(200);
                done();
          });
      });
  });


describe("GET /scolarite/get-students", function() {
      it("Get All Students return with code 200" , function (done){
          request.get(base_url + "/scolarite/get-students" , function(error , response , body){
                expect(response.statusCode).toBe(200);
                done();
          });
      });
  });


describe("POST /scolarite/add-section", function() {
      it("Add Section return with code 200" , function (done){
          request.post({
         url: base_url  + "/scolarite/add-section",
         form : {
              numero : "section1" , 
              anneeScolaire : "1cpi",         
            }
         }
         , function(error , response , body){
                expect(response.statusCode).toBe(200);
                done();
          });
      });
});


  describe("POST /scolarite/add-groupe", function() {
      it("Add Section return with code 200" , function (done){
        var sec = new Section({
              numero : "section1" , 
              anneeScolaire : "1cpi"
            });
        var ObjectId = require('mongodb').ObjectID;
        var gr_id = mongoose.Types.ObjectId(sec._id);
        console.log(gr_id);
    request.post({
         url: base_url  + "/scolarite/add-groupe",
         form : {
            numero : "group1" , 
            section : new Section({
              numero : "section1" , 
              anneeScolaire : "1cpi"
            })._id ,         
            }
         }
         , function(error , response , body){
                expect(response.statusCode).toBe(200);
                done();
          });
      });
  });
/*
  describe("POST /scolarite/add-group", function() {
      it("Add Section return with code 200" , function (done){
        var section = new Section({
              numero : "section1" , 
              anneeScolaire : "1cpi",         
            });
          request.post({
         url: base_url  + "/scolarite/add-section",
         form : {
              numero : "section1" , 
              anneeScolaire : "1cpi",         
            }
         }
         , function(error , response , body){
                console.log(response);
                expect(response.statusCode).toBe(200);
                done();
          });
      });
  });

  /*describe("POST /scolarite/add-student", function() {
      it("Get All Students return with code 200" , function (done){
          request.get(base_url + "/scolarite/get-students" , function(error , response , body){
                expect(response.statusCode).toBe(200);
                done();
          });
      });
  });*/

});