import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DemandeStageComponent } from '../demande-stage/demande-stage.component';
import { ConventionComponent } from '../convention/convention.component';
import { StepperSelectionEvent } from '@angular/cdk/stepper';
import { JournalStageComponent } from '../journal-stage/journal-stage.component';
import { saveAs } from 'file-saver';
import { JournalStageService } from '../../services/journal-stage.service';


@Component({
  selector: 'app-timeline',
  templateUrl: './timeline-component.component.html',
  styleUrls: ['./timeline-component.component.css']
})
export class TimelineComponent {
  demandeStageCompleted: boolean = false; // Initialiser à false
  conventionCompleted: boolean = false;
  journalStageCompleted: boolean = false;

  constructor(private dialog: MatDialog, private journalStageService: JournalStageService) {
    // Initialiser les étapes à la demande de stage
    this.openDemandeStagePopup();
  }

  openDemandeStagePopup() {
    const dialogRef = this.dialog.open(DemandeStageComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe((cin: number | undefined) => {
      console.log('Demande de Stage fermée');
      // Vérifier si le CIN a été saisi et que le PDF a été généré
      if (cin !== undefined) {
        // Si le CIN est saisi, marquer l'étape de la demande de stage comme complétée
        this.demandeStageCompleted = true;
        // Ouvrir la fenêtre modale de la convention uniquement si l'étape de la convention n'est pas complétée
        if (!this.conventionCompleted) {
          this.openConventionPopup();
        }
      }
    });
  }

  openConventionPopup() {
    const dialogRef = this.dialog.open(ConventionComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('Convention fermée');
      // Mettre à jour l'état de la convention une fois fermée
      this.conventionCompleted = true;
    });
  }

  openJournalStagePopup() {
    const dialogRef = this.dialog.open(JournalStageComponent, {
      width: '500px',
    });
  
    dialogRef.afterClosed().subscribe((cin: number | undefined) => {
      console.log("CIN saisi :", cin);
      if (cin) {
        this.journalStageService.generateJournalStageDocument(cin).subscribe(
          (data: Blob) => {
            if (data.size > 0) {
              const blob = new Blob([data], { type: 'application/msword' });
              saveAs(blob, 'journal-stage.docx');
            } else {
              console.error('Erreur lors de la génération du document Word: le document est vide');
            }
          },
          (error: any) => {
            console.error('Erreur lors de la génération du document Word', error?.error?.errorMessage || 'Erreur inattendue');
          }
        );
      }
    });
  }
  
  
  
  
  

  onStepSelectionChange(event: StepperSelectionEvent) {
    if (event.selectedIndex === 2 && !this.journalStageCompleted) {
      this.openJournalStagePopup();
    }
    // Ajoutez la logique supplémentaire pour la sélection de l'étape si nécessaire
  }

  isStageDemandeCompleted(): boolean {
    return this.demandeStageCompleted;
  }

  isConventionCompleted(): boolean {
    return this.conventionCompleted;
  }

  isJournalStageCompleted(): boolean {
    return this.journalStageCompleted;
  }
}