var request = require("request");
const appConfig = require('../config/app-config.js');
var base_url = "http://localhost:3000";
var mongoose = require("mongoose");
mongoose.connect(appConfig.db);
//const Section = require('../model/section.js')(mongoose);
describe("Server test", function () {


// test de Scolarité 
// Teachers
    describe("GET /scolarite/teachers", function () {
        it("Get All Teachers return with code 200", function (done) {
            request.get(base_url + "/scolarite/teachers", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });

// Spécialité
    describe("GET /scolarite/specs", function () {
        it("Get All Groups return with code 200", function (done) {
            request.get(base_url + "/scolarite/specs", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });

// Student 
    describe("GET /scolarite/students", function () {
        it("Get All Students return with code 200", function (done) {
            request.get(base_url + "/scolarite/students", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });

//Modules 
    describe("GET  /scolarite/modules", function () {
        it("Get Modules de Temps return with code 200", function (done) {
            request.get(base_url + "/scolarite/modules", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });


// Notes 
    describe("GET /notes/note-by-student/58e3cb7bf36d283c9c874926", function () {
        it("Get Notes From Student Salah Eddine return with code 200", function (done) {
            request.get(base_url + "/notes/note-by-student/58e3cb7bf36d283c9c874926", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });


// Emploie
    describe("GET  emploi/58fd58577d490c1ddafde29e", function () {
        it("Get Emploie de Temps return with code 200", function (done) {
            request.get(base_url + "/emploi/58fd58577d490c1ddafde29e/", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });


// Suppression d'une salle
/*
describe("POST /salle/id", function() {
      it("Post Teacher return with code 200" , function (done){
          request.get(base_url + "/salles/5909ebee734d1d274bfd404b" , function(error , response , body){
                expect(response.statusCode).toBe(200);
                done();
          });
          
          request.delete(base_url + "/salles/5909ebee734d1d274bfd404b", function(error,response, body) {
                expect(response.statusCode).toBe(200);
                request.get(base_url + "/salles/5909ebee734d1d274bfd404b" , function(error , response , body){
                    expect(response.statusCode).toBe(404);
                    done();
          });
          });
          
      });
  });
  
  // delete Teacher
    describe("GET  scolarite/teacher/:id", function () {
        it("Get Emploie de Temps return with code 200", function (done) {
            request.get(base_url + "/scolarite/teacher/5909e6f8734d1d274bfd3d58", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
            
            request.delete(base_url + "/scolarite/teacher/5909e6f8734d1d274bfd3d58", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                 request.get(base_url + "/scolarite/teacher/5909e6f8734d1d274bfd3d58", function (error, response, body) {
                    expect(response.statusCode).toBe(404);
                done();
                });
            });
            
           
            
        });
    });
  */
});