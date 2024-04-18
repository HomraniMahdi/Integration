import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CandidatureService } from '../../services/candidature.service';
import { FileDetails } from 'src/app/Models/FileDetail';

@Component({
  selector: 'app-candidature-frontee',
  templateUrl: './candidature-frontee.component.html', 
  styleUrls: ['./candidature-frontee.component.css']
})
export class CandidatureFronteeComponent implements OnInit {
  candidatureForm: FormGroup;
  currentStep: number = 1;
  file!: File;
  fileDetails!: FileDetails;
  fileUris: Array<string> = [];


  constructor(private formBuilder: FormBuilder, private candidatureService: CandidatureService, ) {
    this.candidatureForm = this.formBuilder.group({
      cin: ['', Validators.required],
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      numTel: ['', Validators.required],
      titre: ['', Validators.required],
      societe: ['', Validators.required],
      experienceDescription: ['', Validators.required],
      experienceDu: ['', Validators.required],
      experienceAu: ['', Validators.required],
      etablissement: ['', Validators.required],
      specialisation: ['', Validators.required],
      niveauEtude: ['', Validators.required],
      competence: ['', Validators.required],
      formationDu: ['', Validators.required],
      formationAu: ['', Validators.required]
    });
  }

  ngOnInit(): void {

  }

  prevStep() {
    if (this.currentStep > 1) {
      this.currentStep--;
    }
  }

  nextStep() {
    if (this.currentStep < 3) {
      this.currentStep++;
    }
  }
  postulerAvecLinkedIn() {
    console.log('Postuler avec LinkedIn');
  }
 
  selectFile(event: any) {
    this.file = event.target.files.item(0);
  }

  uploadFile() {
    this.candidatureService.upload(this.file).subscribe({
      next: (data) => {
        this.fileDetails = data;
        this.fileUris.push(this.fileDetails.fileUri);
        alert("File Uploaded Successfully")
      },
      error: (e) => {
        console.log(e);
      }
    });
  }
  
  submitCandidature() {
    if (this.candidatureForm.valid) {
      const candidatureData = this.candidatureForm.value;
      this.candidatureService.addCandidature(candidatureData).subscribe(
        (response) => {
          console.log('Candidature ajoutée avec succès :', response);
          alert("Candidature ajoutée avec succès");
          window.location.reload(); 
        },
        (error) => {
          console.error('Erreur lors de l\'ajout de la candidature :', error);
        }
      );
    }
  }
  
}
