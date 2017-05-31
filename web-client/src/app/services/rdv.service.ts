import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class RdvService {

  private baseUrl = 'http://localhost:3000/rdv';
  private appointments = new BehaviorSubject([]);
  constructor(private http :Http) { }
  public addAppointment(studentId, teacherId, reason){
    return this.http.post(`${this.baseUrl}`, {student :studentId, teacher:teacherId, reason :reason})
        .map((res: Response) => res.json())
        .catch((error:any) => Observable.throw(error.json().error || 'Server Error'))
  }

  public getTeacherOppointments(teacherId){
    this.http.get(`${this.baseUrl}/teacher/${teacherId}`)
                      .map((res: Response) => res.json())
                      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'))
      .subscribe((appointments) => this.appointments.next(appointments));
     return this.appointments;
  }

  public getStudentOppointments(studentId){
    this.http.get(`${this.baseUrl}/student/${studentId}`)
                      .map((res: Response) => res.json())
                      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'))
      .subscribe((appointments) => this.appointments.next(appointments));
     return this.appointments;
  }

  public acceptAppointment(appointmentId, date, heur, remarque) {
    return this.http.post(`${this.baseUrl}/accept/${appointmentId}`, {date, heur, remarque})
          .map((res: Response) => res.json())
          .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }

  public rejectAppointment(appointmentId){
    return this.http.get(`${this.baseUrl}/refuse/${appointmentId}`)
          .map((res: Response) => res.json())
          .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));

  }

  public deleteAppointment(appointmentId){
    return this.http.get(`${this.baseUrl}/delete-teacher/${appointmentId}`)
          .map((res: Response) => res.json())
          .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));

  }

  public deleteAppointmentStudent(appointmentId){
    return this.http.get(`${this.baseUrl}/delete-student/${appointmentId}`)
          .map((res: Response) => res.json())
          .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));

  }

  public doneAppointment(appointmentId){
    return this.http.get(`${this.baseUrl}/done/${appointmentId}`)
          .map((res: Response) => res.json())
          .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }

  public updateAppointments(oldOne, newOne){
    let appointments = this.appointments.getValue();
    this.removeAppointment(appointments, oldOne);
    appointments.push(newOne);
    this.appointments.next(appointments);
  }

  private removeAppointment(appointments, appointment){
    for(var i = 0 ; i < appointments.length; i++){
      if(appointments[i]._id == appointment._id) break;
    }
    appointments.splice(i, 1);
  }

}
