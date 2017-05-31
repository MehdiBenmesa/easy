import { Observable } from 'rxjs/Rx';
import { ChooseDateDialogComponent } from '../components/enseignant-components/choose-date-dialog/choose-date-dialog.component';
import { ConfirmDialogComponent } from '../components/enseignant-components/confirm-dialog/confirm-dialog.component';
import { DemandeRdvDialogComponent } from '../components/etudiant-components/demande-rdv-dialog/demande-rdv-dialog.component';
import { MdDialogRef, MdDialog, MdDialogConfig } from '@angular/material';
import { Injectable } from '@angular/core';

@Injectable()
export class DialogService {

    constructor(private dialog: MdDialog) { }

    public chooseDate(title: string, message: string): Observable<any> {

        let dialogRef: MdDialogRef<ChooseDateDialogComponent>;

        dialogRef = this.dialog.open(ChooseDateDialogComponent);
        dialogRef.componentInstance.title = title;
        dialogRef.componentInstance.message = message;

        return dialogRef.afterClosed();
    }

    public confirm(title: string, message: string): Observable<boolean> {

        let dialogRef: MdDialogRef<ConfirmDialogComponent>;

        dialogRef = this.dialog.open(ConfirmDialogComponent);
        dialogRef.componentInstance.title = title;
        dialogRef.componentInstance.message = message;

        return dialogRef.afterClosed();
    }

    public demanderRdv(): Observable<any> {

        let dialogRef: MdDialogRef<DemandeRdvDialogComponent>;
        dialogRef = this.dialog.open(DemandeRdvDialogComponent);

        return dialogRef.afterClosed();
    }
}