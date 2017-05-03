module.exports = function(Spec, Seance){

  function addSeance(groupeId, sectionId, day, seanceSave, callback){
    let seance = new Seance(seanceSave);
    // In this function i have gotten fucked
    seance.save((err, seance) => {
      Spec.findOne({'sections._id': sectionId, 'sections.groupes._id': groupeId},
        (err, spec) => {
          let groupe = spec.sections.id(sectionId).groupes.id(groupeId);
          groupe.emploi[day].push(seance._id);
          spec.save((err, spec) => {
            callback(err, {message : true});
          })
        });
    });
  }

  function getTimeTable(sectionId, groupeId , callback){
    Spec.findOne({'sections._id':sectionId, 'sections.groupes._id' : groupeId},'sections')
      .populate('sections.groupes.emploi.sunday \
                sections.groupes.emploi.monday\
                sections.groupes.emploi.tuesday\
                sections.groupes.emploi.wednesday\
                sections.groupes.emploi.thursday').exec((err, spec) => {
        let emploi = spec.sections.id(sectionId).groupes.id(groupeId).emploi;
        callback(err, emploi);
      });
  }
  function getTimeTableByGroupe(groupeId , callback){
    Spec.findOne({'sections.groupes._id' : groupeId},'sections')
      .populate('sections.groupes.emploi.sunday \
                sections.groupes.emploi.monday\
                sections.groupes.emploi.tuesday\
                sections.groupes.emploi.wednesday\
                sections.groupes.emploi.thursday').exec((err, spec) => {
       // let emploi = spec.sections.groupes.id(groupeId).emploi;
        callback(err, spec);
      });
  }
    return {
      addSeance,
      getTimeTable,
      getTimeTableByGroupe
    };
}
