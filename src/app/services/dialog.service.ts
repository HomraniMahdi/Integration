import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationdialogComponent } from '../pages/confirmationdialog/confirmationdialog.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {
  constructor(private dialog: MatDialog) {}

  openConfirmationDialog(details: any): void {
    this.dialog.open(ConfirmationdialogComponent, {
      data: { details },
    });
  }
  closeConfirmationDialog(): void {
    // Implémentez ici la logique pour fermer le dialogue de confirmation si nécessaire
  }

}
