import { Component, OnInit } from '@angular/core';
import { MdDialogRef } from "@angular/material";
import { XlsxToJsonService } from "../../../services/xlsx-to-json.service";
import { ScolariteService } from "../../../services/scolarite.service";

@Component({
  selector: 'app-new-students',
  templateUrl: './new-students.component.html',
  styleUrls: ['./new-students.component.css']
})
export class NewStudentsComponent implements OnInit {

  public groupe :any;
  public section :any;
  public spec :any;
  private students :any;
  constructor(public dialogRef: MdDialogRef<NewStudentsComponent>,
              private xlsxToJsonService :XlsxToJsonService,
              private scolariteService :ScolariteService) { }

    public handleFile(event) {
          let file = event.target.files[0];
          this.xlsxToJsonService.processFileToJson({}, file).subscribe(data => {
                  this.students = data['sheets'].Sheet1;
                  console.log(this.students);
          })
    }

    public saveStudents(){
      this.scolariteService.saveStudents(this.spec._id, this.section._id, this.groupe._id, this.students ).subscribe(spec => {
        if(spec) this.dialogRef.close();
      });
    }

  ngOnInit() {
  }

}
