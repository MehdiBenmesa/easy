import { Component, OnInit} from '@angular/core';
import { MdDialog } from '@angular/material';
import { SaisieAbsencesComponent } from '../saisie-absences/saisie-absences.component';
import { UserService } from "../../../services/user.service";
import { TeacherService } from "../../../services/teacher.service";
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
    constructor(private dialog:MdDialog,
                private userService :UserService,
                private teacherService :TeacherService){
                  this.userService.getUser().subscribe( (teacher :any) => {
                    this.teacher = teacher;
                    this.teacherService.getGroupes(teacher._id).subscribe(groupes => {
                      this.groupes = groupes;
                    });
                  });

    }
    ngOnInit(){

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
    }
}
