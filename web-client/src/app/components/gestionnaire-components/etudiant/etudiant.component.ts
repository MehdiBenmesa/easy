import { Component, OnInit,Input} from '@angular/core';

@Component({
  selector: 'etudiant',
  templateUrl: 'etudiant.component.html',
  styleUrls:['etudiant.component.css']
})
export class EtudiantComponent implements OnInit{
    @Input() student:any;
    @Input() numero:number;
    @Input() modifiable:boolean;
    show:string="initial";
    maj:any={
      value:"initial"
    };
    constructor(){

    }
    ngOnInit(){

    }

    changerEtat(){
     if(this.show=="show"){
       this.show="hide";
       this.maj.value="hide";
     }
     else{
       if(this.maj.value!="show-form")
          this.show="show";
       }

    }
    modifier(){
      this.maj.value="show-form";
      this.show="hide";
    }
}
