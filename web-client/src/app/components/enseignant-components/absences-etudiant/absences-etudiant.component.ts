import { Component,Input,OnInit,ElementRef} from '@angular/core';
import { MdDialogRef } from '@angular/material';
@Component({
  selector: 'absences-etudiant',
  templateUrl: 'absences-etudiant.component.html',
  styleUrls:['absences-etudiant.component.css']
})
export class AbsencesEtudiantComponent implements OnInit{
    public absences = [];
    constructor(public dialogRef: MdDialogRef<AbsencesEtudiantComponent>){

    }

    ngOnInit(){

    }

}
