import { NgModule ,Injectable} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MaterialModule } from '@angular/material';
import { FormsModule }   from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';
import { MdIcon, MdIconRegistry} from '@angular/material';
import { AppComponent }  from './app.component';
import { DatepickerModule } from 'angular2-material-datepicker';
import { AuthentificationComponent } from './components/authentification/authentification.component'
import { BrowserAnimationsModule  } from '@angular/platform-browser/animations';



import { EspaceGestionnaireComponent } from './components/gestionnaire-components/espace-gestionnaire/espace-gestionnaire.component';
import { ProfilComponent } from './components/gestionnaire-components/profil/profil.component';
import { AnneeScolaireComponent } from './components/gestionnaire-components/annee-scolaire/annee-scolaire.component';
import { FiltreGroupeComponent } from './components/gestionnaire-components/filtre-groupe/filtre-groupe.component';
import { MiseAJourEtudiantComponent } from './components/gestionnaire-components/mise-a-jour-etudiant/mise-a-jour-etudiant.component';
import { EtudiantComponent } from './components/gestionnaire-components/etudiant/etudiant.component';
import { NiveauxSpecialitesComponent } from './components/gestionnaire-components/niveaux-specialites/niveaux-specialites.component';
import { ModulesSemestreComponent } from './components/gestionnaire-components/modules-semestre/modules-semestre.component';

import { EspaceEnseignantComponent } from './components/enseignant-components/espace-enseignant/espace-enseignant.component';
import { EmploisDuTempsComponent } from  './components/enseignant-components/emplois-du-temps/emplois-du-temps.component';
import { EtudiantsComponent } from './components/enseignant-components/etudiants/etudiants.component';
import { ListeGroupeComponent } from './components/enseignant-components/liste-groupe/liste-groupe.component';
import { NotesGroupeComponent } from './components/enseignant-components/notes-groupe/notes-groupe.component';
import { AbsenceGroupeComponent } from './components/enseignant-components/absence-groupe/absence-groupe.component';

import { EspaceEtudiantComponent } from './components/etudiant-components/espace-etudiant/espace-etudiant.component';

import { ProgrammeComponent } from './components/gestionnaire-components/programme/programme.component';
import { TeachersComponent } from './components/gestionnaire-components/teachers/teachers.component';
import { SalleComponent } from './components/gestionnaire-components/salle/salle.component';
import { ListeSalleComponent } from './components/gestionnaire-components/listeSalle/listeSalle.component';
import { SaisiSeanceComponent  } from './components/gestionnaire-components/saisi-seance/saisi-seance.component';
import { AffichageEmploisDuTempsComponent  } from './components/gestionnaire-components/affichage-emplois-du-temps/affichage-emplois-du-temps.component'


import { TeacherComponent } from './components/gestionnaire-components/teacher/teacher.component';
import { ModuleComponent } from './components/gestionnaire-components/module/module.component'
import { Oauth2Component } from './components/oauth2/oauth2.component';
import { UserService } from './services/user.service' ;
import { ScolariteService } from './services/scolarite.service';
import { TeacherService } from './services/teacher.service';
import { AbsenceService } from './services/absence.service'
import { ModuleService } from "./services/module.service";
import { SalleService } from './services/salle.service';
import { EmploiService} from './services/emploi.service';
import { XlsxToJsonService} from './services/xlsx-to-json.service';
import { DialogService } from './services/dialog.service';
import { RdvService } from './services/rdv.service';
import { AbsencesEtudiantComponent } from "./components/enseignant-components/absences-etudiant/absences-etudiant.component";
import { SaisieAbsencesComponent } from "./components/enseignant-components/saisie-absences/saisie-absences.component";
import { StudentTimeTableComponent } from './components/etudiant-components/student-time-table/student-time-table.component';
import { AbsenceStudentComponent } from './components/etudiant-components/absence-student/absence-student.component';
import { AddSpecComponent } from './components/gestionnaire-components/add-spec/add-spec.component';
import { NewStudentsComponent } from './components/gestionnaire-components/new-students/new-students.component';
import { RdvEnseignantComponent } from './components/enseignant-components/rdv-enseignant/rdv-enseignant.component';
import {ChooseDateDialogComponent} from './components/enseignant-components/choose-date-dialog/choose-date-dialog.component';
import {RdvEtudiantComponent} from './components/etudiant-components/rdv-etudiant/rdv-etudiant.component';
import {ConfirmDialogComponent} from './components/enseignant-components/confirm-dialog/confirm-dialog.component';
import {DemandeRdvDialogComponent} from './components/etudiant-components/demande-rdv-dialog/demande-rdv-dialog.component';
import {DetailRDVComponent} from './components/detail-rdv/detail-rdv.component';
import {ReservationEnseignantComponent} from './components/enseignant-components/reservation-enseignant/reservation-enseignant.component'
import {DetailReservationComponent} from './components/detail-reservation/detail-reservation.component'

@NgModule({
  imports:      [
                  BrowserModule,
                  FormsModule,
                  BrowserAnimationsModule,
                  DatepickerModule,
                  RouterModule.forRoot([
                    {
                      path : 'oauth2',
                      component: Oauth2Component
                    },
                    {
                      path:'manager',
                      component: EspaceGestionnaireComponent,
                      children: [
                        { path: 'profile',component: ProfilComponent},
                        { path: 'years',component: AnneeScolaireComponent },
                        { path: 'students',component: FiltreGroupeComponent },
                        { path: 'specs',component: NiveauxSpecialitesComponent },
                        { path: 'program', component: ProgrammeComponent },
                        { path: 'salles', component: ListeSalleComponent },
                        { path: 'teachers', component: TeachersComponent}
                      ]
                    },
                    {
                      path:'teacher',
                      component :EspaceEnseignantComponent,
                      children:[
                        { path:'profile',component: ProfilComponent},
                        { path:'calendar', component: EmploisDuTempsComponent},
                        { path: 'students',component: EtudiantsComponent},
                        { path: 'rdv',component: RdvEnseignantComponent},
                        { path: 'reservation',component: ReservationEnseignantComponent}
                      ]
                    },
                    {
                      path:'student',
                      component:EspaceEtudiantComponent,
                      children: [
                        { path:'profil',component: ProfilComponent},
                        { path:'calendar',component: StudentTimeTableComponent},
                        { path:'absences',component: AbsenceStudentComponent},
                        { path:'rdv' ,component: RdvEtudiantComponent}
                      ]
                    },
                    {
                      path:'',
                      component: AuthentificationComponent
                    }
                 ]),
                  MaterialModule.forRoot()
                ],

  declarations: [ AppComponent,
                  AuthentificationComponent,
                  EspaceGestionnaireComponent,
                  ProfilComponent,
                  AnneeScolaireComponent,
                  NiveauxSpecialitesComponent,
                  ModulesSemestreComponent,
                  FiltreGroupeComponent,
                  MiseAJourEtudiantComponent,
                  EtudiantComponent,
                  EspaceEnseignantComponent,
                  EmploisDuTempsComponent,
                  EtudiantsComponent,
                  ListeGroupeComponent,
                  AbsenceGroupeComponent,
                  NotesGroupeComponent,
                  EspaceEtudiantComponent,
                  Oauth2Component,
                  ProgrammeComponent,
                  ModuleComponent,
                  TeachersComponent,
                  TeacherComponent,
                  SalleComponent,
                  ListeSalleComponent,
                  SaisiSeanceComponent,
                  AffichageEmploisDuTempsComponent,
                  AbsencesEtudiantComponent,
                  SaisieAbsencesComponent,
                  StudentTimeTableComponent,
                  AbsenceStudentComponent,
                  AddSpecComponent,
                  NewStudentsComponent,
                  RdvEnseignantComponent,
                  ChooseDateDialogComponent,
                  RdvEtudiantComponent,
                  DemandeRdvDialogComponent,
                  DetailRDVComponent,
                  ConfirmDialogComponent,
                  ReservationEnseignantComponent,
                  DetailReservationComponent
                  ],
  providers :   [UserService, SalleService, ScolariteService, TeacherService, MdIconRegistry, ModuleService, EmploiService, AbsenceService, XlsxToJsonService, DialogService ,RdvService],
  entryComponents: [
            SaisiSeanceComponent,
            AbsencesEtudiantComponent,
            SaisieAbsencesComponent,
            AddSpecComponent,
            NewStudentsComponent,
            ChooseDateDialogComponent,
            DemandeRdvDialogComponent,
            ConfirmDialogComponent
  ],
  bootstrap:    [ AppComponent ]

})
export class AppModule {


}
