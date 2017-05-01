import { Component ,OnInit} from '@angular/core';
import { MdIconRegistry } from "@angular/material";
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'my-app',
  templateUrl: 'app.component.html'
  //styleUrls:['../../app/app.style.css']
})
export class AppComponent implements OnInit  {

  constructor(private iconRegistry: MdIconRegistry, private sanitizer: DomSanitizer){
    console.log("appConst");
    this.iconRegistry.addSvgIcon('school',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/school.svg'));
    this.iconRegistry.addSvgIcon('person',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/person.svg'));
    this.iconRegistry.addSvgIcon('delete',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/delete.svg'));
    this.iconRegistry.addSvgIcon('edit',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/edit.svg'));
    this.iconRegistry.addSvgIcon('menu',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/menu.svg'));
    this.iconRegistry.addSvgIcon('book',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/books-stack.svg'));
    this.iconRegistry.addSvgIcon('teacher',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/teacher.svg'));
    this.iconRegistry.addSvgIcon('specialite',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/specialite.svg'));
    this.iconRegistry.addSvgIcon('add',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/add.svg'));
    this.iconRegistry.addSvgIcon('portrait',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/portrait.svg'));
    this.iconRegistry.addSvgIcon('groupes',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/groupes.svg'));
    this.iconRegistry.addSvgIcon('rdv',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/rdv.svg'));
    this.iconRegistry.addSvgIcon('reserver',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/reserver.svg'));
    this.iconRegistry.addSvgIcon('note',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/note.svg'));
    this.iconRegistry.addSvgIcon('absence',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/absence.svg'));
    this.iconRegistry.addSvgIcon('liste',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/liste.svg'));
    this.iconRegistry.addSvgIcon('emplois',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/emplois.svg'));
    this.iconRegistry.addSvgIcon('edit-seance',sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/edit-seance.svg'));
}

  ngOnInit(){


  }

  connecter(){
      //this.utilisateurConnecte=true;
      //platformBrowserDynamic().bootstrapModule(AppEnseignantModule);
    }

}
