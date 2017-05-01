import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class ModuleService {

 	private baseUrl = 'http://localhost:3000/scolarite';
  private modules  = new BehaviorSubject([]);
  constructor (private http: Http) {}


  /*ici on récupére les modules par une requete vers la bdd*/
  public getModules(){
     this.http.get(`${this.baseUrl}/modules`)
                      .map((res: Response) => res.json())
                      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'))
      .subscribe((modules) => this.modules.next(modules));
     return this.modules;
  }

  public addModule(module){
    return this.http.post(`${this.baseUrl}/module`, module)
      .map((res: Response) =>{
        res = res.json()
        let values = this.modules.getValue();
        values.push(res);
        this.modules.next(values);
      })
      .catch((error:any) => Observable.throw(error.json().error || 'server error'));
  }

  public deleteModule(module){
    let values = this.modules.getValue();
    let index = values.indexOf(module);
    values.splice(index, 1);
    this.modules.next(values);
    return this.http.delete(`${this.baseUrl}/module/${module._id}`)
      .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }

}
