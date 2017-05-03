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


    return{
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
        checkTeacher
    }
}
