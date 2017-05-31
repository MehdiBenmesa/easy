import { Component,Input,OnInit} from '@angular/core';
import { MdDialogRef} from '@angular/material';

@Component({
  templateUrl: 'demande-rdv-dialog.component.html',
  styleUrls:['demande-rdv-dialog.component.css']
})
export class DemandeRdvDialogComponent implements OnInit{

   listeEns:any[]=[
     "MOSTEFAI Mohamed Amine",
     "BATATA",
     "KODIL",
     "AIT ALI YAHIA DAHBIA",
     "GHOMARI ABDESSAMED"
   ]



   demandeRdv:any={
      objet:"",
      personne:"",
      dateDemande:null,
      dateAcceptation:null,
      heureAcceptation:null,
      heureEffectue:null,
      dateEffectue:null,
      dateRejet:null,
   }


    constructor(public dialogRef: MdDialogRef<DemandeRdvDialogComponent>) {

  }
    ngOnInit(){

    }


}
