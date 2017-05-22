import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class AbsenceService {

 	private baseUrl = 'http://localhost:3000/absences';
  constructor(private http :Http) { }

  public addAbsence(absence):Observable<any>{
    return this.http.post(this.baseUrl, absence)
    .map((res :Response) => res.json())
    .catch((error:any) => Observable.throw(error.json().error || 'server error'))
  }

  public getAbsenceSeance(seance):Observable<any>{
    return this.http.get(`${this.baseUrl}/seance/${seance}`)
    .map((res :Response) => res.json())
    .catch((error:any) => Observable.throw(error.json().error || 'server error'))
  }

  public getAbsencesTeacher(teacher):Observable<any>{
    return this.http.get(`${this.baseUrl}/teacher/${teacher}`)
    .map((res :Response) => res.json())
    .catch((error:any) => Observable.throw(error.json().error || 'server error'));
  }

  public getAbsenceStudent(student) :Observable<any>{
    return this.http.get(`${this.baseUrl}/student/${student}`)
    .map((res :Response) => res.json())
    .catch((error:any) => Observable.throw(error.json().error || 'server error'));
  }
}
