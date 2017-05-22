import { Component, OnInit} from '@angular/core';
import { MdDialog, MdSnackBar } from '@angular/material';
import { SaisieAbsencesComponent } from '../saisie-absences/saisie-absences.component';
import { UserService } from "../../../services/user.service";
import { TeacherService } from "../../../services/teacher.service";
import { AbsenceService } from "../../../services/absence.service";
@Component({
  selector: 'etudiants',
  templateUrl: 'etudiants.component.html',
  styleUrls:['etudiants.component.css']
})
export class EtudiantsComponent implements OnInit{
    classItems:string[]=["item-selectionne","item","item"];
    affichage:number=0;
    private groupes :any = [];
    private teacher :any;
    constructor(private dialog :MdDialog,
                private userService :UserService,
                private teacherService :TeacherService,
                private absenceService :AbsenceService,
                public mdSnackBar :MdSnackBar){
                  this.initiliaze();

    }
    ngOnInit(){

    }

    public initiliaze(){
      this.userService.getUser().subscribe( (teacher :any) => {
        this.teacher = teacher;
        this.teacherService.getGroupes(teacher._id).subscribe(groupes => {
          this.groupes = groupes;
          this.groupes.forEach(groupe => {
            groupe.students.forEach(student => {
              student.absences = [];
            });
          });
            this.absenceService.getAbsencesTeacher(teacher._id).subscribe(absences => {
              this.groupes.forEach(groupe => {
                groupe.students.forEach(student => {
                  absences.forEach(absence => {
                    if(absence.students.includes(student._id)){
                      student.absences.push(absence);
                    }
                  });
                });
              });
            });
        });
      });

    }
    selectionner(i:number){
      this.affichage=i;
      this.classItems=["item","item","item"];
      this.classItems[i]="item-selectionne";
    }

    openSaisieAbsencesDialog(students,  groupe){
      let dialogRef = this.dialog.open(SaisieAbsencesComponent);
      dialogRef.componentInstance.students = students;
      dialogRef.componentInstance.groupe = groupe;
      dialogRef.afterClosed().subscribe((absence) => {
              this.groupes.forEach(groupe => {
                groupe.students.forEach(student => {
                    if(absence.students.includes(student._id)){
                      student.absences.push(absence);
                    }
                });
              });
              this.mdSnackBar.open("Saisi Terminée", "Easy", {
                duration : 2000
              })
            });

    }
}
