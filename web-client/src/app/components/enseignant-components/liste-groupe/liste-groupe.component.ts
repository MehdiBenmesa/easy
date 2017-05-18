import { Component,Input,OnInit} from '@angular/core';

@Component({
  selector: 'liste-groupe',
  templateUrl: 'liste-groupe.component.html',
  styleUrls:['liste-groupe.component.css']
})
export class ListeGroupeComponent implements OnInit{
    @Input() students :any;
    constructor(){
    }
    ngOnInit(){
    }
   ajouterEtudiant(){

   }

}
