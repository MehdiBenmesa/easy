import { Component, OnInit } from '@angular/core';
import { UserService } from "../../../services/user.service";
import { AbsenceService } from "../../../services/absence.service";
import { EmploiService } from "../../../services/emploi.service";

@Component({
  selector: 'app-absence-student',
  templateUrl: './absence-student.component.html',
  styleUrls: ['./absence-student.component.css']
})
export class AbsenceStudentComponent implements OnInit {
  private modules = [];
  constructor(private userService :UserService,
              private absenceService :AbsenceService,
              private emploiService :EmploiService) {
                this.userService.getUser().subscribe((student :any) => {
                  this.absenceService.getAbsenceStudent(student._id).subscribe(absences => {
                    absences.sort(( a , b )=> {
                      return a.date - b.date;
                    })
                    let modules = this.emploiService.getModules();
                    absences.forEach(absence => {
                      modules.forEach(module => {
                        if(absence.seance.module._id == module._id) module.absences.push(absence);
                      });
                    });
                    this.modules = modules;
                  });
                });
   }

  ngOnInit() {
  }

}
