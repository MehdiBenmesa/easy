var request = require("request");
const appConfig = require('../config/app-config.js');
var base_url = "http://localhost:3000";
var mongoose = require("mongoose");
mongoose.connect(appConfig.db);
//const Section = require('../model/section.js')(mongoose);
describe("Server test", function () {



    
// // ********************************************************************************************
// // ********************************************************************************************
// // *******************       START TEST DES SCOLARITE            ******************************
// // ********************************************************************************************
// // ********************************************************************************************

// // test de Scolarité 
// // Teachers
//     describe("GET /scolarite/teachers", function () {
//         it("Get All Teachers return with code 200", function (done) {
//             request.get(base_url + "/scolarite/teachers", function (error, response, body) {
//                 expect(response.statusCode).toBe(200);
//                 done();
//             });
//         });
//     });

// // Spécialité
//     describe("GET /scolarite/specs", function () {
//         it("Get All Groups return with code 200", function (done) {
//             request.get(base_url + "/scolarite/specs", function (error, response, body) {
//                 expect(response.statusCode).toBe(200);
//                 done();
//             });
//         });
//     });


// // Student 
//     describe("GET /scolarite/students", function () {
//         it("Get All Students return with code 200", function (done) {
//             request.get(base_url + "/scolarite/students", function (error, response, body) {
//                 expect(response.statusCode).toBe(200);
//                 done();
//             });
//         });
//     });

//Modules 
    // describe("GET  /scolarite/modules", function () {
    //     it("Get Modules de Temps return with code 200", function (done) {
    //         request.get(base_url + "/scolarite/modules", function (error, response, body) {
    //             expect(response.statusCode).toBe(200);
    //             done();
    //         });
    //     });
    // });
/*
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
    // groupes by teacher and module
// describe("GET  /groupes/:modul/:teacher", function () {
//         it("Get Emploie de Temps de la salle return with code 200", function (done) {
//             request.get(base_url + "/scolarite/groupes/58ffcd658768570fc06c1da3/59074f211e02411bf582457e", function (error, response, body) {
//                 expect(response.statusCode).toBe(200);
//                 done();
//             });
//         });
//     });

    
// ********************************************************************************************
// ********************************************************************************************
// *******************       END TEST DES SCOLARITE            ******************************
// ********************************************************************************************
// ********************************************************************************************


    
// ********************************************************************************************
// ********************************************************************************************
// *******************       START TEST DES NOTES          ******************************
// ********************************************************************************************
// ********************************************************************************************
// Notes 
    // describe("GET /notes/note-by-student/58e3cb7bf36d283c9c874926", function () {
    //     it("Get Notes From Student Salah Eddine return with code 200", function (done) {
    //         request.get(base_url + "/notes/note-by-student/58e3cb7bf36d283c9c874926", function (error, response, body) {
    //             //console.log(body);
    //             expect(response.statusCode).toBe(200);
    //             done();
    //         });
    //     });
    // });    
        // Ajouter NOTE 
/*   describe("Post  /notes/note", function () {
        it("Post note return with code 200", function (done) {
            request.post({
                url : base_url + "/notes/note", 
                form : {
                    reason : "intero",
                    value : 18,
                    student : "58e3cb7bf36d283c9c874926",
                    module: "58ffcde08768570fc06c1da5" 
                }},
                function (error, response, body) {
                expect(response.statusCode).toBe(200);
                console.log(body);
                let bodyJson = JSON.parse(body);
                request.delete(base_url + "/notes/"+bodyJson._id, function(error,response, body) {
                            expect(response.statusCode).toBe(200);
                            });           
                done();
                });
        });
    });*//*
// ********************************************************************************************
// ********************************************************************************************
// *******************       END TEST DES NOTES                  ******************************
// ********************************************************************************************
// ********************************************************************************************

    
// ********************************************************************************************
// ********************************************************************************************
// *******************       START TEST DES EMPLOIES             ******************************
// ********************************************************************************************
// ********************************************************************************************
// Emploie
    describe("GET  emploi/58fd58577d490c1ddafde29e", function () {
        it("Get Emploie de Temps return with code 200", function (done) {
            request.get(base_url + "/emploi/58fd58577d490c1ddafde29e/", function (error, response, body) {
                
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });*/

// Ajouter Seance 
    describe("Post  emploi/seance", function () {
        it("Post seance return with code 200", function (done) {
            request.post({
                url : base_url + "/emploi/seance/", 
                form : {
                    groupeId : "58fd58577d490c1ddafde29e",
                    sectionId :"58fd58577d490c1ddafde29d" ,
                    day : "wednesday",
                    seance : {  
                        module: "58ffcdaa8768570fc06c1da4",
                        salle : "5909ebee734d1d274bfd404b",
                        teacher : "59074db51e02411bf5824574",
                        type : "cours",
                        groupe : "groupe",
                        starts : "10:30",
                        ends : "11:30"
                }}},
                
                function (error, response, body) {
                expect(response.statusCode).toBe(200);
                
                let bodyJson = JSON.parse(body);
               /* request.post({
                    url : base_url + "/delete-seance/",
                    form : {
                        sectionId : bodyJson.sectionId,
                        groupeId : bodyJson.groupeId,
                        seanceId : bodyJson.seanceId,
                        teacherId : bodyJson.seanceId.teacher,
                        salleId : bodyJson.seanceId.salle,
                        day : day}} , 
                function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            })*/
                               
                done();
            });
        });
    });
    /*
// Suppression Seance 
describe("POST /emploi/delete-seance", function() {
      it("DELET Seance return with code 200" , function (done){
          request.post({
                url : base_url + "/emploi/delete-seance/", 
                form : {
                    sectionId :"58ffd8f761150b1ca74a2acc",
                    groupeId : "58ffd8f761150b1ca74a2ace",
                    seanceId : "5909f712149bc73a70720ae5",
                    teacherId : "59074f211e02411bf582457e",     
                    salleId : "5909ebee734d1d274bfd404b",
                    day : "sunday"
 
                }},               
                function (error, response, body) {
                expect(response.statusCode).toBe(200);
                             
                done();
                done();
            });
          
      });
  });
 
  describe("GET  /emploi/salle/5907478ef3e95f19c76d9a3b", function () {
        it("Get Emploie de Temps de la salle return with code 200", function (done) {
            request.get(base_url + "/emploi/salle/5907478ef3e95f19c76d9a3b", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                //console.log(body);
                done();
            });
        });
    });
    
      describe("GET  /emploi/teacher/5907478ef3e95f19c76d9a3b", function () {
        it("Get Emploie de Temps de la salle return with code 200", function (done) {
            request.get(base_url + "/emploi/teacher/59074db51e02411bf5824574", function (error, response, body) {
                expect(response.statusCode).toBe(200);
               // console.log(body);
                done();
            });
        });
    });
*/
    
// ********************************************************************************************
// ********************************************************************************************
// *******************       END TEST DES EMPLOIES               ******************************
// ********************************************************************************************
// ********************************************************************************************


    
// ********************************************************************************************
// ********************************************************************************************
// *******************       START TEST DES ABSENCES             ******************************
// ********************************************************************************************
// ********************************************************************************************

    // Ajouter Absence 
  /*  describe("Post  absences/absence", function () {
        it("Post absence return with code 200", function (done) {
            request.post({
                url : base_url + "/absences/absence", 
                form : {
                    date : "10/05/2017",
                    students : [
                    {
                        "_id" : "58e3cb7bf36d283c9c874926"
                    },
                    {
                        "_id" : "58f77709734d1d33b327b09d"
                    }
                    ],
                    seance: "590956b92ed3c43743cb49b4" 
                }},
                function (error, response, body) {
                expect(response.statusCode).toBe(200);
                let bodyJson = JSON.parse(body);
                request.delete(base_url + "/absences/"+bodyJson._id, function(error,response, body) {
                            expect(response.statusCode).toBe(200);
                            });             
                done();
                });
        });
    });*/

    
// ********************************************************************************************
// ********************************************************************************************
// *******************       END TEST DES ABSENCES             ********************************
// ********************************************************************************************
// ********************************************************************************************


// ********************************************************************************************
// ********************************************************************************************
// *******************       START TEST DES SALLES               ******************************
// ********************************************************************************************
// ********************************************************************************************
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
*/

// ********************************************************************************************
// ********************************************************************************************
// *******************       END TEST DES SALLES             **********************************
// ********************************************************************************************
// ******************************************************************************************** 
// ********************************************************************************************
// ********************************************************************************************
// *******************       START TEST DES RENDEZ-VOUS          ******************************
// ********************************************************************************************
// ********************************************************************************************

// Ajouter Rendez-Vous et Suppression 
  /*  describe("Post  rdv/rdv", function () {
        it("Post Rendez-Vous return with code 200", function (done) {
            request.post({
                url : base_url + "/rdv/rdv", 
                form : {
                    student : "58e3cb7bf36d283c9c874926",
                    teacher : "59074db51e02411bf5824574",
                    reason: "Sujet de PFE" 
                }},
                function (error, response, body) {
                expect(response.statusCode).toBe(200);
                let bodyJson = JSON.parse(body);
                request.delete(base_url + "/rdv/"+bodyJson._id, function(error,response, body) {
                            expect(response.statusCode).toBe(200);
                            });              
                done();
                });
        });
    });
*/
/*
    // rdv by and module
     describe("GET  /rdv/", function () {
        it("Get Rendez-Vous return with code 200", function (done) {
            request.get(base_url + "/rdv/", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });
    
    // Rdv by teacher and module
     describe("GET  /rdv/teacher", function () {
        it("Get Rendez-Vous by teacher return with code 200", function (done) {
            request.get(base_url + "/rdv/teacher/59074db51e02411bf5824574", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });
    
    // Rdv by teacher and module
     describe("GET  /rdv/student", function () {
        it("Get Rendez-Vous by teacher return with code 200", function (done) {
            request.get(base_url + "/rdv/student/58e3cb7bf36d283c9c874926", function (error, response, body) {
                expect(response.statusCode).toBe(200);
                done();
            });
        });
    });
*/
// ********************************************************************************************
// ********************************************************************************************
// *******************       END TEST DES RENDEZ-VOUS          ********************************
// ********************************************************************************************
// ********************************************************************************************

   
});