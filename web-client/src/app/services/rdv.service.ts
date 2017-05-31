import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class RdvService {

  private baseUrl = 'http://localhost:3000/rdv';
  private appointments = new BehaviorSubject([]);
  constructor(private http :Http) { }

  public getTeacherOppointments(teacherId){
    this.http.get(`${this.baseUrl}/teacher/${teacherId}`)
                      .map((res: Response) => res.json())
                      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'))
      .subscribe((appointments) => this.appointments.next(appointments));
     return this.appointments;
  }

}
