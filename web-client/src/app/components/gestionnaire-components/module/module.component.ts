import { Component,Input, ElementRef, OnInit} from '@angular/core';
import { ModuleService } from "../../../services/module.service";

@Component({
  selector: 'module',
  templateUrl: './module.component.html',
  styleUrls:['./module.component.css']
})
export class ModuleComponent implements OnInit{
    @Input() module:any;
    constructor(private moduleService :ModuleService){

    }
    ngOnInit(){

    }

    changerEtat(){
     if(this.module.show == "show"){
       this.module.show="hide";
       this.module.maj="hide";
     }
     else{
       if(this.module.maj!="show-form")
          this.module.show="show";
       }

    }
    modifier(){
      this.module.maj="show-form";
      this.module.show="hide";
    }

    delete(module){
      this.moduleService.deleteModule(module)
        .subscribe( (message ) => console.log(message) );
    }
}
