import { Component,OnInit} from '@angular/core';
import { DialogService } from '../../../services/dialog.service';
@Component({
  selector: 'rdv-etudiant',
  templateUrl: 'rdv-etudiant.component.html',
  styleUrls:['rdv-etudiant.component.css']
})
export class RdvEtudiantComponent implements OnInit{
  result:any;
  confirm:boolean;
  demandesRdv:any[]=[
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      heureEffectue:null,
      dateEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"
      },
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"}
      ];

      rdvAcceptes:any[]=[{
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"}
      ];
      rdvRejetes:any[]=[{
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateRejet:"22/05/2017",
      dateEffectue:null,
      heureEffectue:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateRejet:"22/05/2017",
      dateEffectue:null,
      heureEffectue:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateRejet:"22/05/2017",
      dateEffectue:null,
      heureEffectue:null,
      objet:"Réclamation sur la note d'examen"}
      ];
      rdvEffectues:any[]=[{
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"MOSTEFAI Mohamed Amine",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"}
      ];
    constructor(private dialogService: DialogService){


    }
    ngOnInit(){
    }


   supprimerFromEffectuedRdv(event:any,i:number){
     if(event.target.id=="supprimer")
      this.dialogService
      .confirm("Confirmation", "Voulez-vous vraiment supprimer le RDV")
      .subscribe(res => {
        console.log(res);
        if(res==true)
          this.rdvEffectues.splice(i,1);
          //ici supprimer le rdv de la liste des rdv effectués
      });
   }

   supprimerFromRejectedRdv(event:any,i:number){
     if(event.target.id=="supprimer")
      this.dialogService
      .confirm("Confirmation", "Voulez-vous vraiment supprimer le RDV")
      .subscribe(res => {
        console.log(res);
        if(res==true)
          this.rdvRejetes.splice(i,1);
           //ici supprimer le rdv de la liste des rdv rejetés
      });
   }

   demanderRdv(){
     this.dialogService.demanderRdv().subscribe(res=>{
       if(res!=null) {
            let dateDemande=new Date();
            res.dateDemande=dateDemande.getDate()+"/"+(dateDemande.getMonth()+1)+"/"+dateDemande.getFullYear();
            res.heureRejet=dateDemande.getHours()+":"+dateDemande.getUTCMinutes();
        this.demandesRdv.push(res);
       }
     });
   }





}
