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
    return {
      addNote,
      getNoteByStudent
    };
}
