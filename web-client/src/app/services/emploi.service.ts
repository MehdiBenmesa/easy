import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class EmploiService {
  private baseUrl = 'http://localhost:3000/emploi'
  private groupe = new BehaviorSubject({});
  private emploi = new BehaviorSubject({});
  constructor(private http :Http) {

  }

  public setGroupe(groupe :any){
    this.groupe.next(groupe);
  }

  public getGroupe(){
    return this.groupe;
  }

  public addSeance(groupeId, sectionId, day, seance) {
     return this.http.post(`${this.baseUrl}/seance`, {
      groupeId,
      sectionId,
      day,
      seance
    }).map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'server error'));
  }

  public getTimeTable(sectionId, groupeId){
    this.http.get(`${this.baseUrl}/${sectionId}/${groupeId}`)
    .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'server error'))
      .subscribe(emploi => this.emploi.next(emploi));
      return this.emploi;

  }

}
