import { Component,Input,OnInit} from '@angular/core';
import { MdDialogRef} from '@angular/material';

@Component({
  selector: 'choose-date-dialog',
  templateUrl: 'choose-date-dialog.component.html',
  styleUrls:['choose-date-dialog.component.css']
})
export class ChooseDateDialogComponent implements OnInit{
   rdv={
     date:"",
     heure:""
  }
   title: string;
   message: string;

    constructor(public dialogRef: MdDialogRef<ChooseDateDialogComponent>){

    }
    ngOnInit(){

    }


}
