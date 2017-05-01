import { Component, OnInit, Input } from '@angular/core';
import { TeacherService } from "../../../services/teacher.service";

@Component({
  selector: 'teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {

    @Input() teacher:any;
    constructor(private teacherService :TeacherService){

    }
    ngOnInit(){

    }

    changerEtat(){
     if(this.teacher.show == "show"){
       this.teacher.show="hide";
       this.teacher.maj="hide";
     }
     else{
       if(this.teacher.maj!="show-form")
          this.teacher.show="show";
       }

    }
    modifier(){
      this.teacher.maj="show-form";
      this.teacher.show="hide";
    }

     public delete(teacher){
      this.teacherService.deleteTeacher(teacher)
        .subscribe( (message ) => console.log(message) );
    }

}
