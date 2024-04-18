import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { switchMap, takeUntil } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { ConventionComponent } from 'src/app/pages/convention/convention.component';
import { ConventionFormComponent } from 'src/app/pages/convention-form/convention-form.component';
import { Etudiant } from 'src/app/Models/etudiant';
import { ConventionService } from 'src/app/services/convention.service';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-etudiant-list',
  templateUrl: './etudiant-list.component.html',
  styleUrls: ['./etudiant-list.component.css']
})
export class EtudiantListComponent implements OnInit {
  etudiants: Etudiant[]; // Utilisez un tableau pour stocker les données

  private destroy$: Subject<void> = new Subject<void>();
  dialogService: any;
  

  constructor(
    private etudiantService: EtudiantService,
    private router: Router,
    private notificationService: NotificationService,
    private conventionService: ConventionService,
    private dialog: MatDialog,

  ) {}

  ngOnInit() {
    this.reloadData();
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  reloadData() {
    this.etudiantService.getEtudiantList().pipe(takeUntil(this.destroy$))
      .subscribe(
        (etudiants) => {
          this.etudiants = etudiants; // Affectez à la variable tableau
        },
        (error) => {
          console.error('Error loading etudiants:', error);
        }
      );
  }

  etudiantDetails(etudiantId: number): void {
    this.router.navigate(['/details', etudiantId]);
  }

  deleteEtudiant(etudiantId: number): void {
    this.etudiantService.deleteEtudiant(etudiantId).pipe(
      switchMap(() => this.etudiantService.getEtudiantList())
    ).subscribe(
      (etudiants) => {
        this.etudiants = etudiants; // Affectez à la variable tableau
        console.log('Etudiant deleted successfully');
      },
      (error) => {
        console.error('Error deleting etudiant:', error);
      }
    );
  }

  // Méthode pour afficher la notification de demande de stage
  showDemandeStageNotification(nomEtudiant: string): void {
    this.notificationService.showSuccessNotification(`La demande de stage de ${nomEtudiant} a été effectuée`);
  }
  showConventionForm(cin: number): void {
    this.conventionService.getConventionForm(cin).subscribe(
      (data: any) => {
        console.log(data);
        this.notificationService.showSuccessNotification(`L'étudiant a fourni le formulaire de convention avec succès.`);
        // Rediriger vers la page du formulaire de convention
        this.router.navigate(['/convention-form', cin]);
      },
      (error) => {
        console.error('Erreur lors de la récupération du formulaire de convention :', error);
      }
    );
  }
  
  
}


