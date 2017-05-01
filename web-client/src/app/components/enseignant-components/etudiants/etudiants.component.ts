import { Component, OnInit} from '@angular/core';

@Component({
  selector: 'etudiants',
  templateUrl: 'etudiants.component.html',
  styleUrls:['etudiants.component.css']
})
export class EtudiantsComponent implements OnInit{
    classItems:string[]=["item-selectionne","item","item"];
    affichage:number=0;

    constructor(){

    }
    ngOnInit(){

    }
    selectionner(i:number){
      this.affichage=i;
      this.classItems=["item","item","item"];
      this.classItems[i]="item-selectionne";
    }

}
