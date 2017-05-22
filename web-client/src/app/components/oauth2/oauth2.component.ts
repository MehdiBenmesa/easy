import { Component, OnInit } from '@angular/core';
import { UserService  } from "../../services/user.service";

@Component({
  selector: 'app-oauth2',
  templateUrl: './oauth2.component.html',
  styleUrls: ['./oauth2.component.css']
})
export class Oauth2Component implements OnInit {

    private user : any;
    private token : any;
  constructor(private userService :UserService) {
    let code = window.location.search.split('=')[1];
    let data = this.userService.getData(code);
    data.user.subscribe((user) => {
       this.user = user;
       if(this.user._type == 'Manager') window.location.assign('http://localhost:4200/manager');
       else if(this.user._type == 'Student')window.location.assign('http://localhost:4200/student/calendar');
       else if(this.user._type == 'Teacher')window.location.assign('http://localhost:4200/teacher/calendar');
    });

    data.token.subscribe((token) => {
       this.token = token;
    });
  }
  ngOnInit() {
  }

}
