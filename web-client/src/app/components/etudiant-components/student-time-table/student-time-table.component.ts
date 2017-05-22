import { Component, OnInit } from '@angular/core';
import { EmploiService } from "../../../services/emploi.service";
import { UserService } from "../../../services/user.service";

@Component({
  selector: 'app-student-time-table',
  templateUrl: './student-time-table.component.html',
  styleUrls: ['./student-time-table.component.css']
})
export class StudentTimeTableComponent implements OnInit {
  private emploi :any;
  constructor(private emploiService :EmploiService,
              private userService :UserService) {
                this.userService.getUser().subscribe( (student:any) => {
                  this.emploiService.getTimeTable(student.section, student.groupe).subscribe(emploi => {
                    this.emploi = emploi;
                  })
                })
  }

  ngOnInit() {
  }

}
