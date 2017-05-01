import { Component, OnInit,Input} from '@angular/core';

@Component({
  selector: 'mise-a-jour-etudiant',
  templateUrl: 'mise-a-jour-etudiant.component.html',
  styleUrls:['mise-a-jour-etudiant.component.css']
})
export class MiseAJourEtudiantComponent implements OnInit{
    @Input() student:any;
    @Input() maj:any;
    constructor(){
    }
    ngOnInit(){


    }
    annuler(){
      this.maj.value="hide";
    }

}
