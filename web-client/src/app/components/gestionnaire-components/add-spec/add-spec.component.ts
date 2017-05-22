import { Component, OnInit } from '@angular/core';
import { MdDialogRef } from "@angular/material";
import { ScolariteService } from "../../../services/scolarite.service";

@Component({
  selector: 'app-add-spec',
  templateUrl: './add-spec.component.html',
  styleUrls: ['./add-spec.component.css']
})
export class AddSpecComponent implements OnInit {

  constructor(public dialogRef: MdDialogRef<AddSpecComponent>,
              private scolariteService :ScolariteService) { }
  public save(specName :any){
    let spec = {
      name : specName,
      sections : [
        {
          sectionName : 'Section A',
          groupes : [
            {
              groupeName : 'Groupe 1'
            }
          ]
        }
      ]
    };
    this.scolariteService.addSpec(spec).subscribe(spec => {
      this.dialogRef.close(spec);
    });

  }

  public cancel(){
    this.dialogRef.close();
  }
  ngOnInit() {
  }

}
