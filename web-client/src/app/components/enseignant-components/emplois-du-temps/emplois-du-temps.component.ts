import { Component, OnInit} from '@angular/core';
import { EmploiService } from "../../../services/emploi.service";


@Component({
  selector: 'emplois-du-temps',
  templateUrl: 'emplois-du-temps.component.html',
  styleUrls:['emplois-du-temps.component.css']
})
export class EmploisDuTempsComponent implements OnInit{
    private emploi :any;
    constructor(emploiService :EmploiService){

    }

    ngOnInit(){

    }

}
