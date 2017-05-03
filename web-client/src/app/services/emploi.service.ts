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

  public setEmploi(emploi) {
    this.emploi.next(emploi);
  }

  public getEmploi() {
    return this.emploi.getValue();
  }

  public addSeance(groupeId, sectionId, day, seance) {
     return this.http.post(`${this.baseUrl}/seance`, {
      groupeId,
      sectionId,
      day,
      seance
    }).map((res: Response) => {
      res = res.json();
      let emploi = this.emploi.getValue();
      emploi[day].push(res);
      emploi = this.sortEmploi(emploi);
      this.emploi.next(emploi);
    })
      .catch((error:any) => Observable.throw(error.json().error || 'server error'));
  }

  public deleteSeance(seance){
    return  this.http.post(`${this.baseUrl}/delete-seance`, seance)
    .map((res :Response) => res.json())
    .catch((error:any) => Observable.throw(error.json().error || 'server error'))
    .share();
  }

  public getTimeTable(sectionId, groupeId){
    this.http.get(`${this.baseUrl}/${sectionId}/${groupeId}`)
    .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'server error'))
      .subscribe(emploi => {
        emploi = this.sortEmploi(emploi);
        this.emploi.next(emploi)
      });
      return this.emploi;
  }

  private sortEmploi(emploi :any){
        emploi.sunday.sort((a , b) => {
          return a.starts.split(':')[0] - b.starts.split(':')[0];
        });
        emploi.monday.sort((a , b) => {
          return a.starts.split(':')[0] - b.starts.split(':')[0];
        });
        emploi.tuesday.sort((a , b) => {
          return a.starts.split(':')[0] - b.starts.split(':')[0];
        });
        emploi.wednesday.sort((a , b) => {
          return a.starts.split(':')[0] - b.starts.split(':')[0];
        });
        emploi.thursday.sort((a , b) => {
          return a.starts.split(':')[0] - b.starts.split(':')[0];
        });
        return emploi;
  }



}
