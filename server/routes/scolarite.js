
module.exports = function(express, scolariteController) {

    const router = express.Router();

// get spécialité
    router.get('/specs', (req, res) => {
      scolariteController.getAllSpecs((err, specs) => {
        if(err) throw err;
        res.json(specs);
      });
    });
// ajouter spécialité
    router.post('/spec', (req, res) => {
        scolariteController.addSpec(req.body, (err, spec) => {
            if(err) throw err;
            res.json(spec);
        });
    });


  router.post('/teacher/check', (req, res) => {
    scolariteController.checkTeacher(req.body.teacherId, req.body.day, req.body.starts, req.body.ends, (err, occupied) => {
      if(err) throw err;
      res.json(occupied);
    });
  });

    router.post('/teacher', (req, res) => {
        scolariteController.addTeacher(req.body, (err, teacher) => {
            if(err) throw err;
            res.json(teacher);
        });
    });
// delete teacher
  router.delete('/teacher/:id', (req, res) => {
    scolariteController.deleteTeacher(req.params.id, (err, result) => {
      if(err) throw err;
      res.json(result);
    });
  });

    router.post('/module-to-teacher', (req, res) => {
        scolariteController.addModuleToTeacher(req.body, (err, teacher) => {
            if(err) throw err;
            res.json(teahcer);
        });
    });

    router.post('/manager', (req, res) => {
        scolariteController.addManager(req.body, (err, manager) => {
            if(err) throw err;
            res.json(manager);
        });
    });

    router.post('/student', (req, res) => {
        scolariteController.addStudent(req.body, (err, student) => {
          if(err ) throw err;
          res.json(student);
        });
    });

    router.delete('/module/:id', (req, res ) => {
        scolariteController.deleteModule(req.params.id, (err, module) => {
            if(err) throw err;
            res.json(module);
        });
    });

    router.post('/module', (req, res) => {
        scolariteController.addModule(req.body, (err, module) => {
        if(err) throw err;
        res.json(module);
      });
    });

    router.get('/modules', (req, res) => {
        scolariteController.getAllModules((err, modules) => {
        if(err) throw err;
         res.json(modules);
     });
    });

    router.get('/teachers', (req, res) => {
        scolariteController.getAllTeachers((err, teachers) => {
        if(err) throw err;
        res.json(teachers);
      });
    });

    router.get('/teacher/:id', (req, res) => {
        scolariteController.getTeacher(req.params.id, (err, result) => {
        if(err) throw err;
        res.json(result);
        });

    });

    router.get('/students', (req, res) => {
        scolariteController.getAllStudents((err, students) => {
            if(err) throw err;
            res.json(students);
        });
    });

    router.get('/specs', (req, res) => {
        scolariteController.getSpecs((err, specs) => {
            if(err) throw err;
            res.json(specs);
        });
    });

       // la liste des modules d'un étudiant de id :student
    router.get('/modules-by-student/:student/:groupe/:section',(req,res) => {
            scolariteController.getModuleByStudent(
                req.params.student,
                req.params.groupe,
                req.params.section,
                (err,modules) => {
                if(err) throw err;
                res.json(modules);
            });
        });


    router.get('/modules-by-spec/:section',(req,res) => {
            scolariteController.getModuleBySpec(req.params.section,(err,modules) => {
                if(err) throw err;
                res.json(modules);
            });
        });

    router.get('/teacher/modules/:id',(req,res) => {
        scolariteController.getModuleByTeacher(req.params.id,(err,modules) => {
            if(err) throw err;
            res.json(modules);
        });
    });
    router.get('/groupes', (req,res) => {
        scolariteController.getAllGroupes((err,groupes)=>{
            if(err) throw err;
            res.json(groupes);
        });
    });

     router.get('/groupes/:modul/:teacher', (req,res) => {
        scolariteController.getGroupeByModule(req.params.modul,req.params.teacher, (err,groupes)=>{
            if(err) throw err;
            res.json(groupes);
        })
    })

  router.get('/teacher/groupes/:teacher', (req, res ) => {
    scolariteController.getTeacherGroupes(req.params.teacher, (err, result) => {
      if(err) throw err;
      res.json(result);
    })
  })
   return router;
}
