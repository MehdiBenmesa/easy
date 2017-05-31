module.exports = function(User, Note , NotificationController){

     function addNote(obj  , callback){
        //TODO
        console.log(obj);
        let note = new Note(obj);
        note.save((err, note) => {
        User.findByIdAndUpdate(obj.student,
                    {$push: {notes : note._id}},
                    {new: true}, (err) => {});
            callback(err, note);
        });
        User.findOne({_id : obj.student}, (err, user) => {
                  console.log(user);
                  NotificationController.sendNotification(user , "Vous avez eu "+note.value+" dans le "+note.reason , (err, notification) => {
                      callback(err, notification);
                  } );
                  callback(err, user);
              });

    }

    function updateNote(obj ,callback){
        //TODO
        console.log(obj);
        Note.findOneAndUpdate({
             query: {student : obj.student, reason : obj.reason, module : obj.module},
             update: {$set: {value : obj.value}}
        }, (err,res) => { callback(err,res);});
    }

    function addNotes(obj, callback){
        console.log(obj.students);
        var students = JSON.parse(obj.students);
        //console.log(students);
        for(var i=0;i<students.length;i++){
            addNote(students[i], (err,res)=>{
                callback(err,res);
            });
        }
    }


    function getNoteByStudent(student, callback) {
        User.findOne({_id:student}).populate('notes').exec( (err, student ) => { 
                callback(err, student.notes);
         });
    }
    
    
    function getNoteByModules(studentId, module, callback) {
        User.findOne({_id:studentId}).populate('notes').exec( (err, student ) => {
            let notemodule = student.notes; 
            let notes = [];
            for(var i=0; i< notemodule.length; i++){
                let note = notemodule[i].module;   
                if(note==module){
                    notes.push(notemodule[i]);
                }
            }
            let notesRes = removeDuplicates(notes);
            callback(err, notes);
         });
    }
    function removeDuplicates(arrayIn) {
    var arrayOut = [];
        for (var a=0; a < arrayIn.length; a++) {
            if (arrayOut[arrayOut.length-1] != arrayIn[a]) {
                arrayOut.push(arrayIn[a]);
            }
        }
    }

    function getAllNotes(callback){
        Note.find({}).populate('module').exec((err,res) =>{
            callback(err,res);
        });
    }

    function deleteNote(idNote, callback){
        Note.findByIdAndRemove(idNote, (err, note) => {
             callback(err, {message: 'success'})
        });
    }
    return {
      addNote,
      getNoteByStudent,
      getNoteByModules,
      getAllNotes,
      addNotes,
      deleteNote,
      updateNote
    };
}
