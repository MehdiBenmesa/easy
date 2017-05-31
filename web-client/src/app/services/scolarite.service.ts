import { Injectable } from "@angular/core";
import { Http, Response  } from '@angular/http';
import { Observable  } from "rxjs/Observable";
import { BehaviorSubject } from "rxjs/BehaviorSubject";

@Injectable()
export class ScolariteService{
 	private baseUrl = 'http://localhost:3000/scolarite';
  private specs = new BehaviorSubject([]);
  constructor(private http :Http){}

  public getSpecs(): BehaviorSubject<any[]>{
    this.http.get(`${this.baseUrl}/specs`)
                      .map((res: Response) => res.json())
                      .catch((error:any) => Observable.throw(error.json().error || 'Server Error'))
      .subscribe((specs) => this.specs.next(specs));
      return this.specs;
  }

  public getSpec(): BehaviorSubject<any[]>{
    return this.specs;
  }

  public addSpec(spec) :Observable<any>{
    return this.http.post(`${this.baseUrl}/spec`, spec)
        .map((res: Response) => res.json())
        .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }

  public saveStudents(specId, sectionId, groupeId, students){
    return this.http.post(`${this.baseUrl}/students`, {specId, sectionId, groupeId, students})
        .map((res: Response) => res.json())
        .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));

  }

  public addSection(specId, section) :Observable<any>{
    return this.http.post(`${this.baseUrl}/section`, {specId, section})
        .map((res: Response) => res.json())
        .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }

  public addGroupe(specId, sectionId, groupe) :Observable<any>{
    return this.http.post(`${this.baseUrl}/groupe`, {specId, sectionId, groupe})
        .map((res: Response) => res.json())
        .catch((error:any) => Observable.throw(error.json().error || 'Server Error'));
  }

  public setSpec(spec){
    let specs = this.specs.getValue();
    if(this.containsSpec(specs, spec)){
      this.removeSpec(specs, spec);
    }
    specs.push(spec);
    this.specs.next(specs);
  }

  private containsSpec(specs, newspec){
    let contains = false;
    specs.forEach(spec => {
      if(spec._id == newspec._id){
          contains = true;
      }
    });
    return contains;
  }

  private removeSpec(specs, newspec){
    for(var i = 0 ; i < specs.length; i++){
      if(specs[i]._id == newspec._id) break;
    }
    specs.splice(i, 1);
  }





}
