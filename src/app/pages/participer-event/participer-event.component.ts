import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-participer-event',
  templateUrl: './participer-event.component.html',
  styleUrls: ['./participer-event.component.css']
})
export class ParticiperEventComponent {
  participationForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.participationForm = this.formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      ecole: ['', Validators.required],
      niveau: ['', Validators.required],
      message: ['']
    });
  }

  // Getter pour accéder facilement aux contrôles du formulaire
  get f() { return this.participationForm.controls; }

  onSubmit() {
    // Soumettre le formulaire si valide
    if (this.participationForm.invalid) {
      return;
    }

    // Afficher les valeurs soumises
    console.log('Formulaire soumis :', this.participationForm.value);

    // Réinitialiser le formulaire après soumission
    this.participationForm.reset();
  }
}

