import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class TeacherService {

  constructor(private http :Http) { }

 	private baseUrl = 'http://localhost:3000/scolarite';
  private teachers = new BehaviorSubject([]);

  public getTeachers(){
     this.http.get(`${this.baseUrl}/teachers`)
                      .map((res: Response) => res.json())
                      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'))
      .subscribe((teachers) => this.teachers.next(teachers));
     return this.teachers;
    }

  public addTeacher(teacher){
    return this.http.post(`${this.baseUrl}/teacher`, teacher)
      .map((res: Response) =>{
        res = res.json()
        let values = this.teachers.getValue();
        values.push(res);
        this.teachers.next(values);
      })
      .catch((error:any) => Observable.throw(error.json().error || 'server error'));
  }

  public deleteTeacher(teacher){
    let values = this.teachers.getValue();
    let index = values.indexOf(teacher);
    values.splice(index, 1);
    this.teachers.next(values);
    return this.http.delete(`${this.baseUrl}/teacher/${teacher._id}`)
      .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }
}

