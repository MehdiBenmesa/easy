import { Component,Input,OnInit} from '@angular/core';
import { MdDialogRef} from '@angular/material';
import { TeacherService } from "../../../services/teacher.service";
import { UserService } from "../../../services/user.service";
import { RdvService } from "../../../services/rdv.service";

@Component({
  templateUrl: 'demande-rdv-dialog.component.html',
  styleUrls:['demande-rdv-dialog.component.css']
})
export class DemandeRdvDialogComponent implements OnInit{




    private selectedTeacher :any;
    private reason :any;
    private student :any;
    private teachers = [];
    constructor(public dialogRef: MdDialogRef<DemandeRdvDialogComponent>,
                private teacherService :TeacherService,
                private userService :UserService,
                private rdvService :RdvService) {
                  this.userService.getUser().subscribe(student => {
                    this.student = student;
                      this.teacherService.getTeachers()
                      .subscribe(teachers => {
                        this.teachers = teachers;
                      })
                  })

  }
    ngOnInit(){

    }

    public send(){
      this.rdvService.addAppointment(this.student._id, this.selectedTeacher._id, this.reason)
      .subscribe(res => {
          res.teacher = this.selectedTeacher;
          this.rdvService.updateAppointments({}, res);
          this.dialogRef.close();
      })
    }


}
