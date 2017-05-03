import { Component,Input,OnInit} from '@angular/core';
import { MdDialog } from '@angular/material'
import { SaisiSeanceComponent } from '../saisi-seance/saisi-seance.component';
import { EmploiService } from "../../../services/emploi.service";

@Component({
  selector: 'affichage-emplois-du-temps',
  templateUrl: 'affichage-emplois-du-temps.component.html',
  styleUrls:['affichage-emplois-du-temps.component.css']
})
export class AffichageEmploisDuTempsComponent implements OnInit{
    emploisMAJ :boolean;//à vrai si l'emplois du temps du semestre est mis à jour pour le groupe en cours
    editable :boolean = false;
    private groupe :any;
    private emploi :any;

    constructor(private dialog :MdDialog,
                private emploiService :EmploiService){
                  this.emploiService.getGroupe().subscribe(groupe => {
                    this.groupe = groupe;
                    if(this.groupe._id){
                      this.emploiService.getTimeTable(this.groupe.sectionId, this.groupe._id)
                          .subscribe(emploi => {
                            this.emploi = emploi;
                      });
                   }
                  });
                }

    ngOnInit(){
     this.emploisMAJ = false;

    }
    MAJEmplois(){
      this.editable = true;
    }
    enregistrer(){
      this.editable = false;
    }

   public updateSeance(seance, day){
      let dialogRef = this.dialog.open(SaisiSeanceComponent);
      dialogRef.componentInstance.day = day;
      dialogRef.componentInstance.groupe = this.groupe;
      dialogRef.componentInstance.ancientSeance = seance;
      dialogRef.componentInstance.action = 1;

   }

   public deleteSeance(seance, day) {
      let toBeDeleted = {
        groupeId : this.groupe._id,
        sectionId : this.groupe.sectionId,
        teacherId : seance.teacher._id,
        seanceId : seance._id,
        salleId : seance.salle._id,
        day : day
      }
      this.emploiService.deleteSeance(toBeDeleted)
      .subscribe(result  => {
        if(result.message) {
          let emploi = this.emploiService.getEmploi();
          let index = emploi[day].indexOf(seance);
          if(index > -1){
            emploi[day].splice(index, 1);
          }
          this.emploiService.setEmploi(emploi);
        }
      });

   }

   public addSeance(day :any){
     let dialogRef = this.dialog.open(SaisiSeanceComponent);
     dialogRef.componentInstance.day = day;
     dialogRef.componentInstance.groupe = this.groupe;
     dialogRef.componentInstance.action = 0;
   }
}
