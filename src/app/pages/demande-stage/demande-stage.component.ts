// demande-stage.component.ts
import { Component, EventEmitter, Output } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-demande-stage',
  templateUrl: './demande-stage.component.html',
  styleUrls: ['./demande-stage.component.css']
})
export class DemandeStageComponent {
  cin: number = 0;

  @Output() completed: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private dialogRef: MatDialogRef<DemandeStageComponent>, private httpClient: HttpClient) { }

  submitDemandeStage() {
    // Envoi du CIN au serveur pour générer le PDF
    this.httpClient.get(`http://localhost:9091/demande-stage/pdf/${this.cin}`, { responseType: 'blob' })
    .subscribe(
      response => {
        console.log('Response received from server:', response);
  
        // Crée un objet blob avec la réponse
        const blob = new Blob([response], { type: 'application/pdf' });

        // Crée un objet URL pour le blob, puis ouvre une nouvelle fenêtre
        const fileUrl = URL.createObjectURL(blob);
        window.open(fileUrl);

        // Émettre un événement pour indiquer que la demande de stage est complétée
        this.completed.emit(true);

        // Fermer le popup de demande de stage
        this.dialogRef.close({ completed: true });
      },
      error => {
        // Gérez les erreurs ici
        console.error('Error generating PDF', error);
      }
    );
  }
}
