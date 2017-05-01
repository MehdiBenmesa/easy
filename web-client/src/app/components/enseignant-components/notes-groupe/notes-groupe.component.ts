import { Component,Input,OnInit,ElementRef} from '@angular/core';

@Component({
  selector: 'notes-groupe',
  templateUrl: 'notes-groupe.component.html',
  styleUrls:['notes-groupe.component.css']
})
export class NotesGroupeComponent implements OnInit{
    modifiable:boolean=false;
    focusable:boolean[]=[false,false,false];
    constructor( ){

    }

    ngOnInit(){

    }
  processKeyUp(e:any) {
    if(e.keyCode == 13) {
      let bouton=document.getElementById("ok-button");
      try{
      let next=e.target.parentNode.parentNode.parentNode.nextElementSibling.childNodes[1].lastElementChild.childNodes[0];
        next.focus();
      }
      catch(Exception){
        bouton.focus();
      }
    }
  }

}
