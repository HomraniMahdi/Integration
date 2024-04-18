// convention.component.ts
import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { ConventionService } from '../../services/convention.service';
import { DialogService } from '../../services/dialog.service';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-convention',
  templateUrl: './convention.component.html',
  styleUrls: ['./convention.component.css'],
  providers: [DialogService],
})
export class ConventionComponent {
  cin: string = '';
  nomEntreprise: string = '';
  periodeStage: string = '';
  confirmationDetails: {
    cin:number,
    nom: string,
    prenom: string,
    nomEntreprise: string,
    periodeStage: string,
    // Ajoutez d'autres champs si nécessaire
  } | null = null;

  constructor(
    private conventionService: ConventionService,
    private dialogService: DialogService,
    private notificationService: NotificationService 
  ) {}

  submitForm(): void {
    console.log('Submit form started');
  
    const conventionForm = {
      cin: this.cin,
      nomEntreprise: this.nomEntreprise,
      periodeStage: this.periodeStage,
    };
  
    console.log('Convention form data:', conventionForm);
  
    this.conventionService.submitConventionForm(conventionForm).subscribe(
      (response) => {
        console.log('Server response:', response);
  
        if (response) {
          console.log('Details received:', response);
  
          this.confirmationDetails = {
            nom: response.nom,
            prenom: response.prenom,
            nomEntreprise: response.nomEntreprise,
            periodeStage: response.periodeStage,
            cin: response.cin,
            // Ajoutez d'autres champs si nécessaire
            
          };
  
          console.log('Confirmation details:', this.confirmationDetails);
  
          // Afficher la boîte de dialogue modale avec les détails
          this.dialogService.openConfirmationDialog(this.confirmationDetails);
          this.showConventionNotification(response.nom);
        } else {
          console.error('La réponse du serveur ne contient pas les détails attendus.');
        }
      },
      (error) => {
        console.error('Error submitting form:', error);
        if (error instanceof HttpErrorResponse) {
          console.error('Server returned status:', error.status);
          console.error('Response body:', error.error);
        }
      }
    );
  }
  private showConventionNotification(nom: string): void {
    this.notificationService.showSuccessNotification(`Convention générée pour ${nom}`);
  }
}
