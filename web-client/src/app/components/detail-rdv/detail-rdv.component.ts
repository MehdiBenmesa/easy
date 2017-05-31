import { Component,OnInit,Input} from '@angular/core';
import { DialogService } from "../../services/dialog.service";

@Component({
  selector: 'detail-rdv',
  templateUrl: 'detail-rdv.component.html',
  styleUrls:['detail-rdv.component.css']
})
export class DetailRDVComponent implements OnInit{
    @Input() rdv:any;
    @Input() numRdv:number;
    @Input() typeUtilisateur:string;

    classDetail="initial";
    constructor(private dialogService :DialogService){


    }
    ngOnInit(){

    }
    public accept(rdv){
      console.log(rdv);
     this.dialogService
      .chooseDate("Date RDV", "Veuillez fixer une date pour ce RDV")
      .subscribe(res => {
        if(res!=null){

        }
      });
    }
    open(){
      if( this.classDetail=="show")
        this.classDetail="hide";
      else
        this.classDetail="show";
    }


}
