import { Component, OnInit} from '@angular/core';


@Component({
  selector: 'espace-etudiant',
  templateUrl: 'espace-etudiant.component.html',
  styleUrls:['espace-etudiant.component.css']
})
export class EspaceEtudiantComponent implements OnInit{
    nom:string="RABIA CHERIF";
    prenom:string="Besma";
    classMenus:string[]=["menu-non-selectionne","menu-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne"];

    constructor(){

    }
    ngOnInit(){

    }
    selectionner(i:number){
      this.classMenus=["menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne"];
      this.classMenus[i]="menu-selectionne";
    }

}
