import { Component,Input,OnInit} from '@angular/core';
import { MdDialog} from '@angular/material';
import { AbsencesEtudiantComponent } from './../absences-etudiant/absences-etudiant.component';

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

  afficherDetailsAbsence(){
      this.dialog.open(AbsencesEtudiantComponent);
  }


}
