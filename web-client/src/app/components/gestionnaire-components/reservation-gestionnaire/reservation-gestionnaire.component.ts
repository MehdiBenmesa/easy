import { Component,OnInit} from '@angular/core'; 
import { DialogService } from '../../../services/dialog.service';
@Component({
  moduleId: module.id,
  selector: 'reservation-gestionnaire',
  templateUrl: '../../../../../app/components/gestionnaire-components/reservation-gestionnaire/reservation-gestionnaire.component.html',
  styleUrls:['../../../../../app/components/gestionnaire-components/reservation-gestionnaire/reservation-gestionnaire.component.css']
})
export class ReservationGestionnaireComponent implements OnInit{
  result:any;
  confirm:boolean;
  reservations:any[]=[
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"
      },
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"}
      ];

      reservationsAcceptes:any[]=[{
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:"13/09/2017",
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:"13/09/2017",
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:"13/09/2017",
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
     salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:"13/09/2017",
      dateRejet:null,
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"}
      ];
      reservationsRejetes:any[]=[{
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:"12/09/2017",
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:"12/09/2017",
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"},
      {
      salle:"CP1",
      datePrevue:"15/09/2017",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      dateRejet:"12/09/2017",
      objet:"Séance de ratrappage",
      demandeur:"MOSTEFAI Mohamed Amine"}
      ];
  
    constructor(private dialogService: DialogService){
    
     
    }    
    ngOnInit(){
    }

    onClick(event:any,i:number){
      if(event.target.id=="accepter"){
        this.accepterReservation(i);
      }
      else{
        if(event.target.id=="rejeter")
          this.rejeterReservation(i);
      }
    }


supprimerFromAcceptedReservations(event:any,i:number){
     if(event.target.id=="supprimer")
      this.dialogService
      .confirm("Confirmation", "Voulez-vous vraiment supprimer la reservation")
      .subscribe(res => {
        console.log(res);
        if(res==true)
          this.reservationsAcceptes.splice(i,1);
           //ici supprimer la reservation de la liste des reservations acceptées
      });
   }

   supprimerFromRejectedReservations(event:any,i:number){
     if(event.target.id=="supprimer")
      this.dialogService
      .confirm("Confirmation", "Voulez-vous vraiment supprimer la reservation")
      .subscribe(res => {
        console.log(res);
        if(res==true)
          this.reservationsRejetes.splice(i,1);
           //ici supprimer la reservation de la liste des reservations rejetées
      });
   }




   accepterReservation(i:number){
     this.dialogService
      .confirm("Confirmation", "Vouler vous vraiment accepter la réservation?")
      .subscribe(res => {
        if(res){
          let reservation=this.reservations.splice(i,1)[0];
          let dateAcceptation=new Date();
          reservation.dateAcceptation=dateAcceptation.getDate()+"/"+(dateAcceptation.getMonth()+1)+"/"+dateAcceptation.getFullYear();
          this.reservationsAcceptes.push(reservation);
           //ici supprimer la reservation de la liste des reservations demandées et l'ajouter à la liste des réservations acceptées
        }
      });
   }

   rejeterReservation(i:number){
      this.dialogService
      .confirm("Confirmation", "Voulez-vous vraiment rejeter la reservation")
      .subscribe(res => {

        if(res==true){
            let rdv=this.reservations.splice(i,1)[0];
            let dateRejet=new Date();
            rdv.dateRejet=dateRejet.getDate()+"/"+(dateRejet.getMonth()+1)+"/"+dateRejet.getFullYear();
            rdv.heureRejet=dateRejet.getHours()+":"+dateRejet.getUTCMinutes();
            this.reservationsRejetes.push(rdv);
             //ici supprimer la reservation de la liste des reservations demandées et l'ajouter à la liste des réservations rejetées
        }
      });
   }

}