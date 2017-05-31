import { Component,OnInit} from '@angular/core';
import { DialogService } from '../../../services/dialog.service';
import { RdvService } from "../../../services/rdv.service";
import { UserService } from "../../../services/user.service";

@Component({
  selector: 'rdv-enseignant',
  templateUrl: 'rdv-enseignant.component.html',
  styleUrls:['rdv-enseignant.component.css']
})
export class RdvEnseignantComponent implements OnInit{
  result:any;
  confirm:boolean;
  demandesRdv:any[]=[
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      heureEffectue:null,
      dateEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"
      },
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"}
      ];

      rdvAcceptes:any[]=[{
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:null,
      heureEffectue:null,
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"}
      ];
      rdvRejetes:any[]=[{
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateRejet:"22/05/2017",
      dateEffectue:null,
      heureEffectue:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateRejet:"22/05/2017",
      dateEffectue:null,
      heureEffectue:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:null,
      heureAcceptation:null,
      dateRejet:"22/05/2017",
      dateEffectue:null,
      heureEffectue:null,
      objet:"Réclamation sur la note d'examen"}
      ];
      rdvEffectues:any[]=[{
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"},
      {
      personne:"Rabia Chérif Besma",
      groupe:"2CS-SIL-01",
      dateDemande:"22/05/2017",
      dateAcceptation:"22/05/2017",
      heureAcceptation:"10:30",
      dateEffectue:"22/05/2017",
      heureEffectue:"11:30",
      dateRejet:null,
      objet:"Réclamation sur la note d'examen"}
      ];
      private appointmentDemands = [];
      private appointmentAccepted = [];
      private appointmentRefused = [];
      private appointmentDone = [];
    constructor(private dialogService: DialogService,
                private rdvService :RdvService,
                private userService :UserService){
                  this.userService.getUser().subscribe( (teacher :any) => {
                    this.rdvService.getTeacherOppointments(teacher._id).subscribe(appointments => {
                      appointments.forEach(appointment => {
                        switch(appointment.state){
                          case 'EnAttent':
                            this.appointmentDemands.push(appointment);
                            break;
                          case  'accepted':
                            this.appointmentAccepted.push(appointment);
                            break;
                          case  'refused':
                            this.appointmentRefused.push(appointment);
                            break;
                          case 'done':
                            this.appointmentDone.push(appointment);
                            break;
                        }
                      })
                      console.log(this.appointmentDone);
                      console.log(this.appointmentDemands);
                      console.log(this.appointmentAccepted);
                      console.log(this.appointmentRefused);
                    });
                  });


     }
    ngOnInit(){
    }


}
