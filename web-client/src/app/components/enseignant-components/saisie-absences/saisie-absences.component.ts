import { Component,Input,OnInit} from '@angular/core';
import {MdDialogRef} from '@angular/material';
import { EmploiService } from "../../../services/emploi.service";

@Component({
  selector: 'saisie-absences',
  templateUrl: 'saisie-absences.component.html',
  styleUrls:['saisie-absences.component.css']
})
export class SaisieAbsencesComponent implements OnInit{
 afficherTout:boolean=false;
  public students :any = [];
  public spec :any;
  public section :any;
  public groupe :any;
  private selectedDate :any;
  private seances :any = [];
 constructor(public dialogRef: MdDialogRef<SaisieAbsencesComponent>,
              private emploiService :EmploiService){
        this.selectedDate = new Date();
    }

    ngOnInit(){

    }
    afficher(){
      this.afficherTout=true;
    }
    masquer(){
      this.afficherTout=false;
    }

    public setSeances(){
      let day = this.getSelectedDay(this.selectedDate.getDay());
      this.seances = this.emploiService.getSeances(day);
      console.log(this.seances);
    }

    public save(){
    }

    private getSelectedDay(date){
      switch(date){
        case 0 : return 'sunday';
        case 1 : return 'monday';
        case 2 : return 'thuesday';
        case 3 : return 'wednesday';
        case 4 : return 'thursday';
        default : return null;
      }
    }


}
