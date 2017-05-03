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
    public  ancientSeance :any;
    public  action :any;
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

    public onClick(hStart, mStart, hFinish, mFinish){
      if(this.action == 0) this.addSeance(hStart, mStart, hFinish, mFinish);
      else if(this.action == 1) this.updateSeance(hStart, mStart, hFinish, mFinish);
    }

    public addSeance(hStart, mStart, hFinish, mFinish){
      if(mStart.length == 1) mStart = mStart +'0';
      if(mFinish.length == 1) mFinish = mFinish +'0';
      let seance = {
        module: this.selectedModule._id,
        salle : this.selectedSalle._id,
        teacher : this.selectedTeacher._id,
        type : this.selectedType,
        groupe : this.groupe.spec + " " + this.groupe.section + " " + this.groupe.groupeName,
        starts : hStart +":"+mStart,
        ends : hFinish +":"+mFinish
      }
      this.salleService.checkSalle({
        salleId : this.selectedSalle._id,
        day : this.day,
        starts : hStart + ':' +mStart,
        ends : hFinish + ':' +mFinish
      }).subscribe(result => {
        console.log(result);
      })

      this.emploiService.addSeance(this.groupe._id, this.groupe.sectionId, this.day, seance).subscribe(seance => {
         this.dialogRef.close();
      });
    }

    public deleteSeance(){
      let toBeDeleted = {
        groupeId : this.groupe._id,
        sectionId : this.groupe.sectionId,
        teacherId : this.ancientSeance.teacher._id,
        seanceId : this.ancientSeance._id,
        salleId : this.ancientSeance.salle._id,
        day : this.day
      }
      this.emploiService.deleteSeance(toBeDeleted)
      .subscribe(result  => {
        if(result.message) {
          let emploi = this.emploiService.getEmploi();
          let index = emploi[this.day].indexOf(this.ancientSeance);
          if(index > -1){
            emploi[this.day].splice(index, 1);
          }
          this.emploiService.setEmploi(emploi);
        }
      });
    }

    public updateSeance(hStart, mStart, hFinish, mFinish){
      this.deleteSeance();
      this.addSeance(hStart, mStart, hFinish, mFinish);
    }
}
