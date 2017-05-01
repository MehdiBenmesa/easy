import { Component,OnInit} from '@angular/core';


@Component({
  selector: 'modules-semestre',
  templateUrl: 'modules-semestre.component.html',
  styleUrls:['modules-semestre.component.css']
})
export class ModulesSemestreComponent implements OnInit{

    classListe:string="hide-liste";

    constructor(){

    }
    ngOnInit(){

    }

    clicker(){
      if(this.classListe=="hide-liste")
        this.classListe="show-liste";
      else
        this.classListe="hide-liste";
    }


}
