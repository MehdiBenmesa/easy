import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class SalleService {
  private baseUrl = 'http://localhost:3000/salles';
  private salles  = new BehaviorSubject([]);
  constructor (private http: Http) {}


  /*ici on récupére les salles par une requete vers la bdd*/
  public getSalles(){
    this.http.get(`${this.baseUrl}`)
      .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'))
      .subscribe((salles) => this.salles.next(salles));
    return this.salles;
  }

  public addSalle(salle){
    return this.http.post(`${this.baseUrl}`, salle)
      .map((res: Response) =>{
        res = res.json()
        let values = this.salles.getValue();
        values.push(res);
        this.salles.next(values);
      })
      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }

  public deleteSalle(salle){
    let values = this.salles.getValue();
    let index = values.indexOf(salle);
    values.splice(index, 1);
    this.salles.next(values);
    return this.http.delete(`${this.baseUrl}/${salle._id}`)
      .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }
}

