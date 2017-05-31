import { Component,Input,OnInit} from '@angular/core';
import { MdDialogRef} from '@angular/material';

@Component({
  selector: 'confirm-dialog',
  templateUrl: 'confirm-dialog.component.html',
  styleUrls:['confirm-dialog.component.css']
})
export class ConfirmDialogComponent implements OnInit{

   title: string;
   message: string;

    constructor(public dialogRef: MdDialogRef<ConfirmDialogComponent>){

    }
    ngOnInit(){

    }


}
