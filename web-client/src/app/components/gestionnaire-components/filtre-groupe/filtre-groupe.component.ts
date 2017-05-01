import { Component,OnInit} from '@angular/core';
import { ScolariteService } from "../../../services/scolarite.service";
import { EmploiService } from "../../../services/emploi.service";

@Component({
  selector: 'filtre-groupe',
  templateUrl: 'filtre-groupe.component.html',
  styleUrls:['filtre-groupe.component.css']
})
export class FiltreGroupeComponent implements OnInit{
    private classItems:string[]=["item-selectionne","item","item"];
    private affichage:number = 0;
    private specs = [];
    private selectedSpec :any = {};
    private selectedSection :any = {};
    private selectedGroupe :any = {};
    private students = [];

    public setSelectedSpec(){
      this.students = [];
      this.selectedSpec.sections.forEach(section => {
        section.groupes.forEach(groupe => {
          groupe.students.forEach(student => {
            this.students.push(student);
          })
        });
      });
    }

    public setSelectedSection(){
      this.students = [];
      this.selectedSection.groupes.forEach( groupe => {
          groupe.students.forEach(student => {
            this.students.push(student);
          });
      } );
    }

    public setSelectedGroupe(){
      this.students = [];
      this.selectedGroupe.students.forEach(student => {
        this.students.push(student);
      });
      this.selectedGroupe.section = this.selectedSection.sectionName;
      this.selectedGroupe.sectionId = this.selectedSection._id;
      this.selectedGroupe.spec = this.selectedSpec.name;
      this.emploiService.setGroupe(this.selectedGroupe);
    }

    constructor(private scolariteService :ScolariteService,
                private emploiService : EmploiService){
      this.scolariteService.getSpecs().subscribe(specs => {
        this.specs= specs;
      });

    }

    ngOnInit(){

    }


    selectionner(i:number){
      this.affichage=i;
      this.classItems=["item","item","item"];
      this.classItems[i]="item-selectionne";
    }

   ajouterEtudiant(){

   }

}
