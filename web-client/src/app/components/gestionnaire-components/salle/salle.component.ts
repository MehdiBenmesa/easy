import { Component,Input, ElementRef, OnInit} from '@angular/core';
import { SalleService } from "../../../services/salle.service";

/// Le composant du salle
@Component({
  selector: 'salle',
  templateUrl: './salle.component.html',
  styleUrls:['./salle.component.css']
})
export class SalleComponent implements OnInit{
  @Input() salle:any;
  constructor(private salleService :SalleService){

  }
  ngOnInit(){

  }

  changerEtat(){
    if(this.salle.show == "show"){
      this.salle.show="hide";
      this.salle.maj="hide";
    }
    else{
      if(this.salle.maj!="show-form")
        this.salle.show="show";
    }

  }
  modifier(){
    this.salle.maj="show-form";
    this.salle.show="hide";
  }

  delete(salle){
    this.salleService.deleteSalle(salle)
      .subscribe( (message ) => console.log(message) );
  }
}
