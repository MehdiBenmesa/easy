import { Component,OnInit} from '@angular/core';
import { DialogService } from '../../../services/dialog.service';
@Component({
  selector: 'reservation-enseignant',
  templateUrl: 'reservation-enseignant.component.html',
  styleUrls:['reservation-enseignant.component.css']
})
export class ReservationEnseignantComponent implements OnInit{
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

}
