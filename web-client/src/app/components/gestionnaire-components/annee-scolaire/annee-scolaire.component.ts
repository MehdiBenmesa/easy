import { Component,Input, ElementRef, OnInit} from '@angular/core';


@Component({
  selector: 'annee-scolaire',
  templateUrl: 'annee-scolaire.component.html',
  styleUrls:['annee-scolaire.component.css']
})
export class AnneeScolaireComponent implements OnInit{
    annees:string[]=["2009/2010","2010/2011","2011/2012","2012/2013","2013/2014","2014/2015","2015/2016","2016/2017"];

    constructor(){

    }
    ngOnInit(){

    }
}
