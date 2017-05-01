import { Component,Input,OnInit,ElementRef} from '@angular/core';
import { MdDialogRef } from '@angular/material';
import { ModuleService } from "../../../services/module.service";
import { TeacherService } from "../../../services/teacher.service";
import { SalleService } from "../../../services/salle.service";
import { EmploiService } from "../../../services/emploi.service";

@Component({
  selector: 'saisi-seance',
  templateUrl: 'saisi-seance.component.html',
  styleUrls:['saisi-seance.component.css']
})
export class SaisiSeanceComponent implements OnInit{

    public  day: any;
    public  groupe :any;
    private selectedType :any;
    private selectedTeacher :any;
    private selectedSalle :any;
    private selectedModule :any;
    private salles:any[];
    private  teachers :any[];
    private  modules:any[];
    constructor(public dialogRef: MdDialogRef<SaisiSeanceComponent>,
                private moduleService :ModuleService,
                private teacherService :TeacherService,
                private salleService :SalleService,
                private emploiService :EmploiService){
        this.moduleService.getModules().subscribe(modules => this.modules = modules);
        this.teacherService.getTeachers().subscribe(teachers => this.teachers = teachers);
        this.salleService.getSalles().subscribe(salles => this.salles = salles);
    }

    ngOnInit(){

    }

    public addSeance(hStart, mStart, hFinish, mFinish){
      let seance = {
        module: this.selectedModule._id,
        salle : this.selectedSalle._id,
        teacher : this.selectedTeacher._id,
        type : this.selectedType,
        starts : hStart +":"+mStart,
        ends : hFinish +":"+mFinish
      }

      this.emploiService.addSeance(this.groupe._id, this.groupe.sectionId, this.day, seance).subscribe(result => {
         if(result.message == true) this.dialogRef.close();
      });

    }




}
