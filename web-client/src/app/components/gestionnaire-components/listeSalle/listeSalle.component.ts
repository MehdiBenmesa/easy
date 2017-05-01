import { Component,Input, ElementRef, OnInit} from '@angular/core';
import { SalleService } from "../../../services/salle.service";

/// Le composant de l'enssemble des salles
@Component({
  selector: 'listeSalle',
  templateUrl: './listeSalle.component.html',
  styleUrls:['./listeSalle.component.css']
})
export class ListeSalleComponent implements OnInit{

  private salles:any[];
  private showAdd:any = 'initial';
  private selectedType = {};

  newSalle:any= {
    num:"",
    nom:"",
    show:"initial",
    maj:"initial"
  };



  l: number;
  cpt: number;
  p: number;
  j: number;
  pre: boolean;
  numPage: number;

  constructor(private salleService : SalleService){
    this.salleService.getSalles().subscribe(salles => {
        this.salles = salles;
        this.salles.forEach((salle) => {
          salle.show = 'initial';
          salle.maj = 'initial';
        });
      }
    );
  }



  ngOnInit(){

  }

  changerEtat(salle:any){
    if(this.salles[this.salles.indexOf(salle)].show=="show")
      this.salles[this.salles.indexOf(salle)].show="hide";
    else
      this.salles[this.salles.indexOf(salle)].show="show";
  }

  annuler(){
    this.showAdd = "hide";
  }

  add(salle:any){
    salle.type = this.selectedType;
    console.log(this.selectedType)
    console.log(salle);
    this.salleService.addSalle(salle).subscribe((salle) => this.salles.concat(salle));
    this.showAdd="hide";
  }



  public changerTab(id: number, ide: string) {
    console.log(ide);
    /*var x;
     if (id == 1) {
     if (this.l < this.j && this.j > 5) {
     for (x = 0; x < this.j; x++) {
     ens.setAttribute('style', 'display: none');
     }
     if (this.pre && this.l != 0) {
     this.l += 5;
     }
     if (this.l == 0) {
     this.l = 5;
     }
     this.cpt = this.l + 5;
     while ((this.l < this.j) && (this.l < this.cpt)) {
     ens.setAttribute('style', 'visibility: visible');
     this.l++;
     }
     this.pre = false;
     this.p++;
     }
     } else if (id == 2) {

     if (this.l >= 5) {
     for (x = 0; x < this.j; x++) {
     ens.setAttribute('style', 'display: none');
     }
     if (this.cpt >= this.l && !this.pre) {
     this.l -= 5 - (this.cpt - this.l);
     }
     this.cpt = this.l - 5;
     while ((this.l > 0) && (this.l > this.cpt)) {
     ens.setAttribute('style', 'visibility: visible');
     this.l--;
     }
     this.pre = true;
     this.p--;
     }
     } else if (id == 3) {
     if (this.l > 5) {
     for (x = 0; x < this.j; x++) {
     ens.setAttribute('style', 'display: none');
     }
     this.l = 5;
     this.cpt = 0;
     while ((this.l > 0) && (this.l > this.cpt)) {
     ens.setAttribute('style', 'visibility: visible');
     this.l--;
     }
     this.pre = true;
     this.p = 1;
     }
     } else if (id == 4) {
     if (this.l < this.j) {
     for (x = 0; x < this.j; x++) {
     ens.setAttribute('style', 'display: none');
     }
     this.l = this.j - (this.j % 5);
     this.cpt = this.l + 5;
     while ((this.l < this.j) && (this.l < this.cpt)) {
     ens.setAttribute('style', 'visibility: visible');
     this.l++;
     }
     this.pre = false;
     this.p = Math.ceil(this.l / 5);
     }
     }
     this.numPage++;*/
  }
}
