import { Component, OnInit, Input} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from "../../../services/user.service";

@Component({
  selector: 'espace-enseignant',
  templateUrl: 'espace-enseignant.component.html',
  styleUrls:['espace-enseignant.component.css']
})

export class EspaceEnseignantComponent implements OnInit{
    private user: any;
    constructor(private userService: UserService){
      this.userService.getUser().subscribe( user => {
         this.user = user
      });
    }

    classMenus:string[]=["menu-non-selectionne","menu-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne"];



    ngOnInit(){


    }

    selectionner(i:number){
      this.classMenus=["menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne"];
      this.classMenus[i]="menu-selectionne";
    }

}
