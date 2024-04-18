// etudiant-new.component.ts

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Etudiant } from 'src/app/Models/etudiant';
import { EtudiantService } from 'src/app/services/etudiant.service';


@Component({
  selector: 'app-etudiant-new',
  templateUrl: './etudiant-new.component.html',
  styleUrls: ['./etudiant-new.component.css']
})
export class EtudiantNewComponent implements OnInit {
  etudiant: Etudiant;
  submitted = false;
  errorMessage: string = '';

  constructor(private etudiantService: EtudiantService, private router: Router) {}

  ngOnInit(): void {
    this.submitted = false;
    this.etudiant = new Etudiant(0, 0, '', '', 0, '', false, []); 
  }

  save() {
    // Generate email based on nom and prenom
    this.etudiant.email = `${this.etudiant.nom.toLowerCase()}.${this.etudiant.prenom.toLowerCase()}@example.com`;

    console.log('Request payload:', this.etudiant);
    this.etudiantService.createEtudiant(this.etudiant).subscribe(
      (createdEtudiant) => {
        console.log('Etudiant created successfully:', createdEtudiant);
        // Handle success (if needed)
        
        // Rediriger vers l'interface des listes d'étudiants après avoir créé un nouvel étudiant
        this.router.navigate(['/etudiant']);
      },
      (error) => {
        console.error('Error creating etudiant:', error);
        this.errorMessage = 'Error creating etudiant. Please check the input data.';
      }
    );
  }

  onSubmit() {
    this.submitted = true;
    this.errorMessage = ''; // Clear any previous error message
    this.save();
  }
}