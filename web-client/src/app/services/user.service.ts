import { Injectable } from '@angular/core';
import { Http, Response  } from '@angular/http';
import { Observable  } from "rxjs/Observable";
import { Subject  } from "rxjs/Subject";
import { BehaviorSubject } from "rxjs/BehaviorSubject";
import 'rxjs/Rx';

@Injectable()
export class UserService {

	constructor(private http: Http) { }
  private token = new BehaviorSubject({});
  private user = new BehaviorSubject({});

  public authenticate(){
    return this.http.get('http://localhost:3000/users/oauth2-url')
        .map((response :Response) => response.json())
        .catch((error :any) => Observable.throw(error.json().error || 'Server Error'));

  }

  public getData(code){
    this.http.get(`http://localhost:3000/users/oauth2-tokens/${code}`)
               .map((response :Response) => response.json())
               .catch((error :any) => Observable.throw(error.json().error || 'Server Error'))
               .subscribe((data) => {
                  this.token.next(data.tokens);
                  this.user.next(data.user);
                  localStorage.setItem('user', JSON.stringify(data.user));
                  localStorage.setItem('token', JSON.stringify(data.tokens));
               });
               return {
                 token : this.token,
                 user : this.user
               }
  }

  public getUser(){
    this.user.next(JSON.parse(localStorage.getItem('user')));
    return this.user;
  }

  public getToken(){
    this.token.next(JSON.parse(localStorage.getItem('token')));
    return this.token;
  }

}
