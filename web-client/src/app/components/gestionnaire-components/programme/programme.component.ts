import { Component,Input, ElementRef, OnInit} from '@angular/core';
import { ModuleService } from "../../../services/module.service";

/// Le composant de l'enssemble des modules
@Component({
  selector: 'programme',
  templateUrl: './programme.component.html',
  styleUrls:['./programme.component.css']
})
export class ProgrammeComponent implements OnInit{

  private modules:any[];
  private showAdd:any = 'initial';

  newModule:any= {
    num:"",
    nom:"",
    abreviation:"",
    coefficient:"",
    description:"",
    volumeHoraire:"",
    show:"initial",
    maj:"initial"
  };



  l: number;
  cpt: number;
  p: number;
  j: number;
  pre: boolean;
  numPage: number;

  constructor(private moduleService : ModuleService){
    this.moduleService.getModules().subscribe(modules => {
      this.modules = modules;
        this.modules.forEach((module) => {
          module.show = 'initial';
          module.maj = 'initial';
        });
      }
    );
  }



  ngOnInit(){

  }

  changerEtat(module:any){
    if(this.modules[this.modules.indexOf(module)].show=="show")
      this.modules[this.modules.indexOf(module)].show="hide";
    else
      this.modules[this.modules.indexOf(module)].show="show";
  }

  annuler(){
    this.showAdd = "hide";
  }

  add(module:any){
    this.moduleService.addModule(module).subscribe((module) => this.modules.concat(module));
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
