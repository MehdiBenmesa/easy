import { Component,Input, ElementRef, OnInit} from '@angular/core';
@Component({
  selector: 'groupe',
  templateUrl: 'groupe.component.html',
  styleUrls:['groupe.component.css']
})
export class GroupeComponent implements OnInit{
   niveaux:string[]=["1CPI","2CPI","1CS","2CS-SIL","2CS-SIT","2CS-SIG"];
    sections:string[]=["A","B","C","D"];
    groupes:string[]=["G01","G02","G03","G04","G05","G06","G07","G08"];
   formClass:string="initial";
   ajoutSection:boolean=false;
   ajoutGroupe:boolean=false;
   nouveauEtudiant:any=
        { num:"",
            matricule:"",
            nom:"",
            prenom:"",
            email:"",
            adress:"",
            tel:"",
            show:"",
            maj:"initial"
            };
  etudiants:any[]=[
     {
       num:"01",
       matricule:"2013/701",
       nom:"Rabia Cherif",
       prenom:"Rabia Cherif",
       email:"db_rabia_cherif@esi.dz",
       adress:"19 Rue Petit Port Ain Bénian Alger",
       tel:"055002211",
       show:"initial",
       maj:"initial"
     },
     {
       num:"02",
       matricule:"2013/701",
       nom:"Rabia Cherif",
       prenom:"Rabia Cherif",
       email:"db_rabia_cherif@esi.dz",
       adress:"19 Rue Petit Port Ain Bénian Alger",
       tel:"055002211",
       show:"initial",
       maj:"initial"
     },
     {
       num:"03",
       matricule:"2013/701",
       nom:"Rabia Cherif",
       prenom:"Rabia Cherif",
       email:"db_rabia_cherif@esi.dz",
       adress:"19 Rue Petit Port Ain Bénian Alger",
       tel:"055002211",
       show:"initial",
       maj:"initial"
     },
     {
       num:"04",
       matricule:"2013/701",
       nom:"Rabia Cherif",
       prenom:"Rabia Cherif",
       email:"db_rabia_cherif@esi.dz",
       adress:"19 Rue Petit Port Ain Bénian Alger",
       tel:"055002211",
       show:"initial",
       maj:"initial"
     }
     ];
    constructor(){

    }
    ngOnInit(){
    }
   ajouterEtudiant(){

   }

}
