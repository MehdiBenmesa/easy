import { Component,Input,OnInit} from '@angular/core';
import { MdDialog} from '@angular/material';
import { AbsencesEtudiantComponent } from './../absences-etudiant/absences-etudiant.component';
import { AbsenceService } from "../../../services/absence.service";

@Component({
  selector: 'absence-groupe',
  templateUrl: 'absence-groupe.component.html',
  styleUrls:['absence-groupe.component.css']
})
export class AbsenceGroupeComponent implements OnInit{


    @Input() students :any;
    @Input() teacher :any;
    constructor(private dialog:MdDialog,
                private absenceService :AbsenceService){


    }
    ngOnInit(){
    }

  afficherDetailsAbsence(){
      this.dialog.open(AbsencesEtudiantComponent);
  }


}
