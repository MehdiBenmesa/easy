import { Component,Input,OnInit} from '@angular/core';
import { MdDialog} from '@angular/material';
import { AbsencesEtudiantComponent } from './../absences-etudiant/absences-etudiant.component';
import { AbsenceService } from "../../../services/absence.service";
import { UserService } from "../../../services/user.service";

@Component({
  selector: 'absence-groupe',
  templateUrl: 'absence-groupe.component.html',
  styleUrls:['absence-groupe.component.css']
})
export class AbsenceGroupeComponent implements OnInit{

    @Input() students :any;
    constructor(private dialog:MdDialog){


    }
    ngOnInit(){
    }

  afficherDetailsAbsence(student){
      let dialogRef = this.dialog.open(AbsencesEtudiantComponent);
      dialogRef.componentInstance.absences = student.absences;

  }


}
