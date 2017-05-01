import { Component, OnInit, Input} from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'espace-enseignant',
  templateUrl: 'espace-enseignant.component.html',
  styleUrls:['espace-enseignant.component.css']
})

export class EspaceEnseignantComponent implements OnInit{
    enseignant:any={
        firstName:"MOSTEFAI",
        lastName:"Mohamed Amine",
        adresse:"Adresse",
        adresseMail:"Adresse Mail",
        tel:"055555555"
      };

    classMenus:string[]=["menu-non-selectionne","menu-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne"];


    constructor(private route: ActivatedRoute){
       console.log("enseignant");
       console.log(this.enseignant);
    }

    ngOnInit(){


    }

    selectionner(i:number){
      this.classMenus=["menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne"];
      this.classMenus[i]="menu-selectionne";
    }

}
