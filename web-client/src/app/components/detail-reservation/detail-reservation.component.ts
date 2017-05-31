import { Component,OnInit,Input} from '@angular/core';

@Component({
  selector: 'detail-reservation',
  templateUrl: 'detail-reservation.component.html',
  styleUrls:['detail-reservation.component.css']
})
export class DetailReservationComponent implements OnInit{
    @Input() reservation:any;
    @Input() numReservation:number;
    @Input() typeUtilisateur:string;

    classDetail="initial";
    constructor(){


    }
    ngOnInit(){

    }

    open(){
      if( this.classDetail=="show")
        this.classDetail="hide";
      else
        this.classDetail="show";
    }


}
