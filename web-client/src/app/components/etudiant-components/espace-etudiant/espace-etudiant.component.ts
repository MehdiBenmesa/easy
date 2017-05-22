import { Component, OnInit} from '@angular/core';
import { UserService } from "../../../services/user.service";


@Component({
  selector: 'espace-etudiant',
  templateUrl: 'espace-etudiant.component.html',
  styleUrls:['espace-etudiant.component.css']
})
export class EspaceEtudiantComponent implements OnInit{
    classMenus:string[]=["menu-non-selectionne","menu-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne"];
    private student :any;
    constructor(private userService :UserService){
      this.userService.getUser().subscribe(student => {
        this.student = student;
      })

    }
    ngOnInit(){

    }
    selectionner(i:number){
      this.classMenus=["menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne","menu-non-selectionne"];
      this.classMenus[i]="menu-selectionne";
    }

}
