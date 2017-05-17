import { Component, OnInit} from '@angular/core';
import { EmploiService } from "../../../services/emploi.service";
import { UserService } from "../../../services/user.service";


@Component({
  selector: 'emplois-du-temps',
  templateUrl: 'emplois-du-temps.component.html',
  styleUrls:['emplois-du-temps.component.css']
})
export class EmploisDuTempsComponent implements OnInit{
    private emploi :any;
    constructor(private emploiService :EmploiService,
                private userService :UserService){
                  this.userService.getUser().subscribe(user => {
                    this.emploiService.getTeacherTimeTable(user).subscribe(emploi => {
                      this.emploi = emploi;
                    });
                  });

    }

    ngOnInit(){

    }

}
