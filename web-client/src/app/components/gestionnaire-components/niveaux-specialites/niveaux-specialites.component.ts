import { Component, OnInit} from '@angular/core';
import { ScolariteService } from "../../../services/scolarite.service";
import { MdDialog } from "@angular/material";
import { AddSpecComponent } from "../add-spec/add-spec.component";
import { XlsxToJsonService } from "../../../services/xlsx-to-json.service";
import { NewStudentsComponent } from "../new-students/new-students.component";


@Component({
  selector: 'niveaux-specialites',
  templateUrl: 'niveaux-specialites.component.html',
  styleUrls:['niveaux-specialites.component.css']
})
export class NiveauxSpecialitesComponent implements OnInit{
    open:boolean=false;
    private specs :any[] = [];
    private selectedSpec :any = null;
    private selectedSection :any = null;
    private selectedGroupe :any = null;
    constructor(private scolariteService :ScolariteService,
                private dialog:MdDialog){
      this.scolariteService.getSpec().subscribe(specs => {
        this.specs = specs;
      });
    }

    public addSpec(){
      let dialogRef = this.dialog.open(AddSpecComponent);
      dialogRef.afterClosed().subscribe(spec => {
        if(spec){
          this.scolariteService.setSpec(spec);
        }
      });
    }
    public addSection(sectionName){
      let section = {
        sectionName : sectionName,
          groupes : [
            {
              groupeName : 'Groupe 1'
            }
          ]
      }
      this.scolariteService.addSection(this.selectedSpec._id, section).subscribe(spec => {
        this.scolariteService.setSpec(spec);
      })
    }

    public addGroupe(groupeName){
      let groupe = {
        groupeName : groupeName
      }

      this.scolariteService.addGroupe(this.selectedSpec._id, this.selectedSection._id, groupe).subscribe(spec => {
        this.scolariteService.setSpec(spec);
      });
    }

    public addStudents(spec, section, groupe){
      let dialogRef = this.dialog.open(NewStudentsComponent);
      dialogRef.componentInstance.groupe = groupe;
      dialogRef.componentInstance.spec = spec;
      dialogRef.componentInstance.section = section;

    }
    ngOnInit(){

    }
}
