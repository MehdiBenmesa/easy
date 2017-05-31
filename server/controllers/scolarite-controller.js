module.exports = function( Student, Manager, Teacher,  Spec, Module){

    function addManager(obj, callback) {
        let manager = new Manager(obj);
        manager.save( (err, manager) => {
            callback(err, manager);
        } );
    }

  function deleteTeacher(id, callback) {
    Teacher.find({_id : id}).remove((err, result) => {
      callback(err, result);
    });
  }

  function checkTeacher(teacherId, day,  starts, ends, callback){
    let occupied = false;
    Teacher.findOne({_id :teacherId}, (err, teacher) => {
      teacher.populate('emploi.sunday emploi.monday emploi.tuesday emploi.wednesday emploi.thursday', (err, teacher) => {
        teacher.emploi[day].forEach(seance => {
          let a = seance.starts.replace(':', '');
          let b = seance.ends.replace(':', '');
          starts = starts.replace(':', '');
          ends = ends.replace(':', '');
          if( Math.max(b, ends) - Math.min(a, starts) < (b - a) + (ends - starts) ){
            occupied = true;
          }
        });
        callback(err, {occupied});

      })
    });
  }

    function addTeacher(obj, callback) {
        let teacher = new Teacher(obj);
        teacher.save( (err, teacher) => {
            callback(err, teacher);
        } );
    }

    function addModuleToTeacher(obj, callback) {
        Teacher.findByIdAndUpdate(obj.teahcer, {$push :{modules : obj.module}}, {new : true}, (err, teacher) => {
            callback(err, teacher);
        });
    }


    function addStudent(obj, callback){
        let student = new Student(obj);
        student.save( (err, student) => {
            callback(err, student);
        } );
    }

    function addSpec(obj, callback) {
        let spec = new Spec(obj);
        spec.save( (err, spec) => {
            callback(err, spec);
        } );
    }

  function addSection(specId, section, callback){
    Spec.findOne( {'_id': specId}, (err, spec) => {
      spec.sections.push(section);
      spec.save((err, spec) => {
        callback(err, spec);
      });
    });
  }

  function addGroupe(specId, sectionId, groupe, callback){
    Spec.findOne({'_id' :specId}, (err, spec) => {
      spec.sections.id(sectionId).groupes.push(groupe);
      spec.save((err, spec) => {
        callback(err, spec);
      });
    });
  }

  function getAllSpecs(callback){
    Spec.find().populate('sections.groupes.students').exec((err, specs) => {
        callback(err, specs);
    });
  }

    function addModule(obj, callback) {
        let module = new Module(obj);
        module.save( (err, module) => {
            callback(err, module);
     } );
    }

    function deleteModule(id, callback) {
        Module.findByIdAndRemove(id, (err, module) => {
           callback(err, {message: 'success'});
        });
    }

    function getAllModules(callback) {
        Module.find({}, (err, modules) => {
            callback(err, modules);
    });
    }

    function getAllTeachers(callback) {
        Teacher.find({_type :'Teacher'}).populate('modules').exec((err, teachers) => {
            callback(err, teachers);
    });
    }

    function getAllStudents(callback) {
        Student.find({}, (err, students) => {
            callback(err, students);
        });
    }

    function getSpecs(callback) {
        Spec.find({}, (err, specs) => {
            callback(err, specs);
        });
    }

    function getTeacher(id , callback){
        Teacher.findOne({_id : id}, (err,teacher) => {
            callback(err,teacher);
        });
    }

    function getModuleByStudent(sectionId,groupeId,callback) {
            Spec.findOne({'sections._id':sectionId, 'sections.groupes._id' : groupeId},'sections')
      .populate('sections.groupes.emploi.sunday\
                sections.groupes.emploi.monday\
                sections.groupes.emploi.tuesday\
                sections.groupes.emploi.wednesday\
                sections.groupes.emploi.thursday').exec((err, spec) => {

        let emploi = spec.sections.id(sectionId).groupes.id(groupeId).emploi;
        let modules = [];
        for(var i=0;i<emploi.sunday.length;i++){
            modules.push(emploi.sunday[i].module);
        }
        for(i;i<emploi.monday.length;i++){
            modules.push(emploi.monday[i].module);
        }
        for(i;i<emploi.tuesday.length;i++){
            modules.push(emploi.tuesday[i].module);
        }
        for(i;i<emploi.wednesday.length;i++){
            modules.push(emploi.wednesday[i].module);
        }
        for(i;i<emploi.thursday.length;i++){
            modules.push(emploi.thursday[i].module);
        }
        let moduleRes = removeDuplicates(modules);

        callback(err, moduleRes);
    });
    }

    function removeDuplicates(arrayIn) {
    var arrayOut = [];
    for (var a=0; a < arrayIn.length; a++) {
        if (arrayOut[arrayOut.length-1] != arrayIn[a]) {
            arrayOut.push(arrayIn[a]);
        }
    }
    return arrayOut;
}

    function getModuleBySpec(sectionId ,callback) {
        Spec.findOne({'sections._id': sectionId},
        (err, spec) => {
            spec.populate('courses.course courses.teacher', (err, spec) => {
                  let modules = spec.courses;
                  callback(err, modules);
            });
        });
    }

    function getAllGroupes(callback) {
         Spec.findOne({}).populate({
                    path: 'sections'
                    }).populate({
                        path: 'groupes'}).exec( (err, response ) => {
                        let groupes = response.sections.groupes;
                        callback(err, response);
                    });
    }

    function getModuleByTeacher(teacherId, callback){
        Teacher.findOne({_id : teacherId}, 'modules').populate('modules').exec( (err, result) => {
            let modules = result.modules;
            callback(err,modules);
        });
    }

    function getGroupeByModule(moduleId,teacherId,callback){
    Teacher.findOne({'_id': teacherId, 'courses.course' : moduleId}, (err, teacher) => {
        if(teacher!=null){
        teacher.courses.forEach(course => {
                Spec.find({'sections.groupes._id': { $in : course.groupes }}).populate('sections.groupes sections.groupes.students').exec((err, result) => {
                    let groupes = [];
                    result.forEach(spec => {
                    spec.sections.forEach(section => {
                        section.groupes.forEach(groupe => {
                        if(teacher.groupes.indexOf(groupe._id) != -1){
                            groupe = groupe.toObject();
                            groupe.section = section.sectionName;
                            groupe.spec = spec.name;
                            groupes.push(groupe);
                        }
                        });
                    });
                });
                callback(err, groupes);
            });
        });
    }else
    callback(err,teacher);
        });
    }
  function getTeacherGroupes(teacherId, callback){
    Teacher.findOne({'_id': teacherId}, (err, teacher) => {
        if(teacher!=null){
        teacher.courses.forEach(course => {
        Spec.find({'sections.groupes._id': { $in : teacher.groupes }}).populate('sections.groupes.students').exec((err, result) => {
        let groupes = [];
        result.forEach(spec => {
          spec.sections.forEach(section => {
            section.groupes.forEach(groupe => {
              if(teacher.groupes.indexOf(groupe._id) != -1){
                groupe = groupe.toObject();
                groupe.section = section.sectionName;
                groupe.spec = spec.name;
                groupes.push(groupe);
              }
            });
          });
        });

        callback(err, groupes);
      });
    });
        }else
            {if (err) {
            return res.send();
            }
            callback(err,teacher);
            }
    });

  }

    return {
        addStudent,
        getAllStudents,
        addSpec,
        getSpecs,
        addManager,
        addModule,
        deleteModule,
        getAllTeachers,
        getAllModules,
        addTeacher,
        addModuleToTeacher,
        getAllSpecs,
        deleteTeacher,
        getModuleByStudent,
        getModuleBySpec,
        getAllGroupes,
        getTeacher,
        checkTeacher,
        getModuleByTeacher,
        getGroupeByModule,
        getTeacherGroupes,
        addSection,
        addGroupe
    }
}
