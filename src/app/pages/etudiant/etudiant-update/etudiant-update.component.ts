// etudiant-update.component.ts

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { tap, switchMap } from 'rxjs/operators';
import { Etudiant } from 'src/app/Models/etudiant';
import { EtudiantService } from 'src/app/services/etudiant.service';

@Component({
  selector: 'app-etudiant-update',
  templateUrl: './etudiant-update.component.html',
  styleUrls: ['./etudiant-update.component.css']
})
export class EtudiantUpdateComponent implements OnInit {
  id: any;
  etudiant: Etudiant;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private etudiantService: EtudiantService
  ) {}

  ngOnInit() {
    this.etudiant = new Etudiant(0, 0, '', '', 0, '', false, []); 

    this.id = this.route.snapshot.params['id'];

    this.etudiantService.getEtudiant(this.id).subscribe(
      (data) => {
        this.etudiant = data;
        this.updateEmail(); // Update email initially
      },
      (error) => console.log(error)
    );
  }

  private updateEmail() {
    // Generate email based on nom and prenom
    this.etudiant.email = `${this.etudiant.nom.toLowerCase()}.${this.etudiant.prenom.toLowerCase()}@example.com`;
  }

  onSubmit() {
    console.log('Submit button clicked');
    this.updateEtudiant();
  }
  
  updateEtudiant() {
    console.log('Updating etudiant:', this.etudiant);
    this.etudiantService.updateEtudiant(this.id, this.etudiant)
      .pipe(
        switchMap(() => this.etudiantService.getEtudiant(this.id)), // Fetch updated etudiant
        tap((updatedEtudiant) => {
          console.log('Etudiant updated successfully');
          this.etudiant = updatedEtudiant; // Update the component's etudiant with the latest data
          this.updateEmail(); // Update email after modification
          this.router.navigate(['/etudiant']);
        })
      )
      .subscribe(
        () => {},
        (error) => {
          console.log('Error updating etudiant:', error);
        }
      );
  }
}
