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
      classDetails :any;
      private j = 0;
      private appointmentDemands = [];
      private appointmentAccepted = [];
      private appointmentRefused = [];
      private appointmentDone = [];
    constructor(private dialogService: DialogService,
                private rdvService :RdvService,
                private userService :UserService){
                  this.userService.getUser().subscribe( (teacher :any) => {
                    this.rdvService.getTeacherOppointments(teacher._id).subscribe(appointments => {
                        this.appointmentDemands = [];
                        this.appointmentAccepted = [];
                        this.appointmentRefused = [];
                        this.appointmentDone = [];
                        this.classDetails = new Array(appointments.length).fill('initial');
                      appointments.forEach(appointment => {
                        if(appointment.supTeacher == false)
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


    public accept(appointment){
     this.dialogService
      .chooseDate("Date RDV", "Veuillez fixer une date pour ce RDV")
      .subscribe( (res) => {
        this.rdvService.acceptAppointment(appointment._id, res.date, res.hour, res.remarque)
        .subscribe(res => {
          res.student = appointment.student;
          this.rdvService.updateAppointments(appointment, res);
        })
      });
    }

    public reject(appointment){
      this.dialogService
      .confirm("Confirmation", "Voulez-vous vraiment rejeter le RDV")
      .subscribe(res => {
        if(res == true){
          this.rdvService.rejectAppointment(appointment._id)
            .subscribe(res => {
              res.student = appointment.student;
              this.rdvService.updateAppointments(appointment, res);
            })
        }
      });

    }

    public done(appointment){
     this.dialogService
     .confirm("Confirmation", "Vous avez effectuer ce Rendez-vous")
     .subscribe(res => {
       if(res == true){
        this.rdvService.doneAppointment(appointment._id)
            .subscribe(res => {
              res.student = appointment.student;
              this.rdvService.updateAppointments(appointment, res);
            })
       }
     });
    }

    public delete(appointment){
      this.dialogService
      .confirm("Confirmation", "Voulez-vous vraiment supprimer le RDV")
      .subscribe(res => {
        if(res == true){
          this.rdvService.deleteAppointment(appointment._id)
            .subscribe(res => {
              res.student = appointment.student;
              this.rdvService.updateAppointments(appointment, res);
            });
        }
      })
    }

    open(index){
      if( this.classDetails[index]=="show")
        this.classDetails[index]="hide";
      else
        this.classDetails[index]="show";
    }

}
