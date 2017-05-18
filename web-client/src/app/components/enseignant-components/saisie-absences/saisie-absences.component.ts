import { Component,Input,OnInit} from '@angular/core';
import {MdDialogRef} from '@angular/material';
import { EmploiService } from "../../../services/emploi.service";
import { AbsenceService } from "../../../services/absence.service";

@Component({
  selector: 'saisie-absences',
  templateUrl: 'saisie-absences.component.html',
  styleUrls:['saisie-absences.component.css']
})
export class SaisieAbsencesComponent implements OnInit{
 afficherTout:boolean=false;
  public students :any = [];
  public groupe :any;
  private absenceSeance :any;
  private selectedDate :any;
  private seances :any = [];
  private selectedSeance :any;
  private selectedStudents :any[] = [];

 constructor(public dialogRef: MdDialogRef<SaisieAbsencesComponent>,
              private emploiService :EmploiService,
              private absenceService :AbsenceService){
        this.selectedDate = new Date();
    }

    ngOnInit(){

    }
    public afficher(){
      this.afficherTout = true;
    }

    masquer(){
      this.afficherTout = false;
    }

    public setSeances(){
      let day = this.getSelectedDay(this.selectedDate.getDay());
      this.seances = this.emploiService.getSeances(day)
    }

    public getAbsences(){
      this.absenceService.getAbsenceSeance(this.selectedSeance._id).subscribe(res => {
        this.absenceSeance = res;
        console.log(this.absenceSeance)
      })
    }

    public printAbsence(student, absence){
      return absence.students.includes(student._id)?'A':'P';
    }

    public save(){
      let absence = {
        date : this.selectedDate,
        students : this.selectedStudents,
        seance : this.selectedSeance._id
      }
      this.absenceService.addAbsence(absence).subscribe(absence => {
        console.log(absence);
      });
    }

    public setStudent(student, source ){
      if(source.checked){
        this.selectedStudents.push(student._id);
      }else{
        this.selectedStudents.splice(this.selectedStudents.indexOf(student._id), 1);
      }
    }

    private getSelectedDay(date){
      switch(date){
        case 0 : return 'sunday';
        case 1 : return 'monday';
        case 2 : return 'thuesday';
        case 3 : return 'wednesday';
        case 4 : return 'thursday';
        case 5 : return 'friday';
        case 6 : return 'saturday';
        default : return null;
      }
    }


}
