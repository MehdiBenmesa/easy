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




}
