import { Component,OnInit} from '@angular/core';
import { DialogService } from '../../../services/dialog.service';
import { RdvService } from "../../../services/rdv.service";
import { UserService } from "../../../services/user.service";
import { MdDialog } from "@angular/material";
import { DemandeRdvDialogComponent } from "../demande-rdv-dialog/demande-rdv-dialog.component";
@Component({
  selector: 'rdv-etudiant',
  templateUrl: 'rdv-etudiant.component.html',
  styleUrls:['rdv-etudiant.component.css']
})
export class RdvEtudiantComponent implements OnInit{
  result:any;
  confirm:boolean;
      classDetails :any;
      private j = 0;
      private appointmentDemands = [];
      private appointmentAccepted = [];
      private appointmentRefused = [];
      private appointmentDone = [];
    constructor(private dialogService: DialogService,
                private rdvService :RdvService,
                private userService :UserService,
                private dialog :MdDialog){
                  this.userService.getUser().subscribe( (student:any) => {
                    this.rdvService.getStudentOppointments(student._id).subscribe(appointments => {
                        this.appointmentDemands = [];
                        this.appointmentAccepted = [];
                        this.appointmentRefused = [];
                        this.appointmentDone = [];
                        this.classDetails = new Array(appointments.length).fill('initial');
                      appointments.forEach(appointment => {
                        if(appointment.supStudent == false)
                        switch(appointment.state){
                          case 'enattent':
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
                    });
                  });



    }
    ngOnInit(){
    }


    open(index){
      if( this.classDetails[index]=="show")
        this.classDetails[index]="hide";
      else
        this.classDetails[index]="show";
    }


    public delete(appointment){
      this.dialogService
      .confirm("Confirmation", "Voulez-vous vraiment supprimer le RDV")
      .subscribe(res => {
        if(res == true){
          this.rdvService.deleteAppointmentStudent(appointment._id)
            .subscribe(res => {
              res.teacher = appointment.teacher;
              this.rdvService.updateAppointments(appointment, res);
            });
        }
      })
    }
    public demanderRdv(){
      this.dialog.open(DemandeRdvDialogComponent);
    }



}
