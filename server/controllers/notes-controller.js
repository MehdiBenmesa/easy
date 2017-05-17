module.exports = function(Student, Note){

    function addNote(obj, callback){
        //TODO
       let note = new Note(obj);
        note.save((err, note) => {
            Student.findByIdAndUpdate(obj.student,
                    {$push: {notes : note._id}},
                    {new: true}, (err) => {});
            callback(err, note);
        });
    }


    function getNoteByStudent(student, callback) {
        Student.findOne({_id:student}).populate('notes').exec( (err, student ) => { 
                callback(err, student.notes);
         });
    }
    
    function getNoteByModules(student,module, callback) {
        Student.findOne({_id:student}).populate('notes').exec( (err, student ) => {
            let notemodule = student.notes; 
            let notes = [];
            for(var i=0;i< notemodule.length;i++){
                let note = notemodule[i].module;   
                 
                if(note==module){
                    notes.push(student.notes[i]);
                }
            }
            //let notesRes = removeDuplicates(notes);
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
    return {
      addNote,
      getNoteByStudent,
      getNoteByModules
    };
}
