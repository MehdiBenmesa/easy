import { Component, OnInit} from '@angular/core';
import { UserService } from "../../../services/user.service";


@Component({
  selector: 'espace-gestionnaire',
  templateUrl: 'espace-gestionnaire.component.html',
  styleUrls:['espace-gestionnaire.component.css']
})
export class EspaceGestionnaireComponent implements OnInit{
    classMenus:string[]=["menu-non-selectionne","menu-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne" ];

    private user: any;
    constructor(private userService: UserService){
      this.userService.getUser().subscribe( user => {
         this.user = user
      });
    }

    ngOnInit(){

    }

    selectionner(i:number){
      this.classMenus=["menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne", "menu-non-selectionne"];
      this.classMenus[i]="menu-selectionne";
    }
}
