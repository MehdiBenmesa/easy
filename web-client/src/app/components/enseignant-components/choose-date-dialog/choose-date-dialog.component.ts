import { Component,Input,OnInit} from '@angular/core';
import { MdDialogRef} from '@angular/material';

@Component({
  selector: 'choose-date-dialog',
  templateUrl: 'choose-date-dialog.component.html',
  styleUrls:['choose-date-dialog.component.css']
})
export class ChooseDateDialogComponent implements OnInit{
  private selectedDate :any;
  private hour :any;
  private remarque :any;
   title: string;
   message: string;

    constructor(public dialogRef: MdDialogRef<ChooseDateDialogComponent>){
        this.selectedDate = new Date();
    }
    ngOnInit(){

    }


}
