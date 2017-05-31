import { Component,OnInit,Input} from '@angular/core'; 

@Component({
  moduleId: module.id,
  selector: 'detail-reservation',
  templateUrl: '../../../../../app/components/detail-reservation/detail-reservation.component.html',
  styleUrls:['../../../../../app/components/detail-reservation/detail-reservation.component.css']
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