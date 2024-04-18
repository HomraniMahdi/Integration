import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-journal-stage',
  templateUrl: './journal-stage.component.html',
  styleUrls: ['./journal-stage.component.css']
})
export class JournalStageComponent {
  cin: number | undefined;

  constructor(public dialogRef: MatDialogRef<JournalStageComponent>) { }

  closeDialog(): void {
    if (this.cin) {
      this.dialogRef.close(this.cin);
    }
  }
}
