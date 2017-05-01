import { Component, OnInit } from '@angular/core';
import { ModuleService } from "../../../services/module.service";
import { TeacherService } from "../../../services/teacher.service";

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  private modules:any[];
  private teachers:any[];
  private showAdd:any = 'initial';
  private selectedModules :any = [];




  l: number;
  cpt: number;
  p: number;
  j: number;
  pre: boolean;
  numPage: number;

  constructor(private teacherService: TeacherService, private moduleService: ModuleService ){
    this.moduleService.getModules().subscribe(modules => {
      this.modules = modules;
    });

    this.teacherService.getTeachers().subscribe(teachers => {
      this.teachers = teachers;
      this.teachers.forEach(teacher => {
        teacher.show = 'initial'
        teacher.maj = 'initial'
      })
    });
  }

  add(teacher:any){
    teacher.modules = [];
    this.selectedModules.forEach(module => {
        teacher.modules.push(module._id);
    });
    this.teacherService.addTeacher(teacher).subscribe((teacher) => this.teachers.concat(teacher));
    this.showAdd="hide";
  }


  ngOnInit(){

  }

  changerEtat(teacher:any){
    if(this.modules[this.teachers.indexOf(teacher)].show=="show")
      this.modules[this.teachers.indexOf(teacher)].show="hide";
    else
      this.modules[this.teachers.indexOf(teacher)].show="show";
  }

  annuler(){
    this.showAdd = "hide";
  }



}
