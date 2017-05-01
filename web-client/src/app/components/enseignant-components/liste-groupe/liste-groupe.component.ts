import { Component,Input,OnInit} from '@angular/core';

@Component({
  selector: 'liste-groupe',
  templateUrl: 'liste-groupe.component.html',
  styleUrls:['liste-groupe.component.css']
})
export class ListeGroupeComponent implements OnInit{
  etudiants:any[]=[
     {
       matricule:"2013/701",
       firstName:"Rabia Cherif",
       lastName:"Rabia Cherif",
       adresseMail:"db_rabia_cherif@esi.dz",
       adresse:"19 Rue Petit Port Ain Bénian Alger",
       tel:"055002211",
       groupe:{
            numero:1,
            section:null,
          }
     },
     {
       matricule:"2013/701",
       firstName:"Rabia Cherif",
       lastName:"Rabia Cherif",
       adresseMail:"db_rabia_cherif@esi.dz",
       adresse:"19 Rue Petit Port Ain Bénian Alger",
       tel:"055002211",
       groupe:{
            numero:1,
            section:null,
          }
     },
     {
       matricule:"2013/701",
       firstName:"Rabia Cherif",
       lastName:"Rabia Cherif",
       adresseMail:"db_rabia_cherif@esi.dz",
       adresse:"19 Rue Petit Port Ain Bénian Alger",
       tel:"055002211",
       groupe:{
            numero:1,
            section:null,
          }
     },
     {
       matricule:"2013/701",
       firstName:"Rabia Cherif",
       lastName:"Rabia Cherif",
       adresseMail:"db_rabia_cherif@esi.dz",
       adresse:"19 Rue Petit Port Ain Bénian Alger",
       tel:"055002211",
       groupe:{
            numero:1,
            section:null,
          }
     }
     ];
    constructor(){
       console.log("liste");
    }
    ngOnInit(){
    }
   ajouterEtudiant(){

   }

}
