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

    return {
      addNote
    };
}
