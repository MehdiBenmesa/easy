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
  emploisMAJ:boolean;//à vrai si l'emplois du temps du semestre est mis à jour pour le groupe en cours
  editable:boolean=false;
    private groupe :any;
    private emploi :any;

    constructor(private dialog:MdDialog,
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
     this.emploisMAJ=false;
       //Normalement on vérifie ici si l'emplois à été mis à jour

    }
    MAJEmplois(){
      this.editable = true;
    }
    enregistrer(){
      this.editable = false;
    }

   modifierSeance(){
      this.dialog.open(SaisiSeanceComponent);
   }

   public addSeance(day :any){
     let dialogRef = this.dialog.open(SaisiSeanceComponent);
     dialogRef.componentInstance.day = day;
     dialogRef.componentInstance.groupe = this.groupe;
   }
}
