import { Component, OnInit } from '@angular/core';
import { ActivatedRoute ,Router} from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CandidatureService } from 'src/app/services/candidature.service';

@Component({
  selector: 'app-form-modify-cand',
  templateUrl: './form-modify-cand.component.html',
  styleUrls: ['./form-modify-cand.component.css']
})
export class FormModifyCandComponent implements OnInit {
  modifyForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private candidatureService: CandidatureService,
    private router: Router,
    private route: ActivatedRoute
  ){}

  ngOnInit(): void {
   
    this.modifyForm = this.formBuilder.group({
      cin: ['', Validators.required],
      disponibilite: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      image: ['', Validators.required],
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      numTel: ['', Validators.required],
      status: ['', Validators.required]
    });

   
    const idCandidature = this.route.snapshot.params['id'];
  console.log('ID Candidature in FormModifyCandComponent:', idCandidature);

   
    this.candidatureService.getCandidatureById(idCandidature).subscribe(
      (candidature: any) => {
        this.modifyForm.patchValue({
          cin: candidature.cin,
          disponibilite: candidature.disponibilite,
          email: candidature.email,
          numTel: candidature.numTel,
          nom: candidature.nom,
          prenom: candidature.prenom,
          status: candidature.status
        });
      },
      (error) => {
        console.error('Erreur lors de la récupération des données de la candidature :', error);
      }
    );
  }

  onSubmit() {
    const idCandidature = this.route.snapshot.params['id'];
    const formData = this.modifyForm.value;

    this.candidatureService.updateCandidature(idCandidature, formData).subscribe(
      () => {
        console.log('Candidature mise à jour avec succès.');
        this.router.navigate(['/candidature']); 
      },
      (error) => {
        console.error('Erreur lors de la mise à jour de la candidature :', error);
      }
    );
  }
  
}
