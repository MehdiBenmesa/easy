import { Component,Input, ElementRef, OnInit} from '@angular/core';
import { UserService } from "../../services/user.service";


@Component({
  selector: 'authentification',
  templateUrl: 'authentification.component.html',
  styleUrls:['authentification.component.css']
})
export class AuthentificationComponent implements OnInit{

  private ifError = false;
  constructor(private userService:UserService){}
  public authenticate(){
    this.userService.authenticate()
    .subscribe((obj) => {
      window.location.assign(obj.url);
    }, error => {
       this.ifError = true ;
    });
  }


    ngOnInit(){
    }
}
