import { Component ,OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EntretienModel } from 'src/app/Models/EntretienModel';
import { EntretienService } from 'src/app/services/entretien.service';


@Component({
  selector: 'app-entretien',
  templateUrl: './entretien.component.html',
  styleUrls: ['./entretien.component.css']
})
export class EntretienComponent implements OnInit {
  entretienForm: FormGroup;
  submitted = false;
  entretiens: EntretienModel[] = [];

  constructor(private formBuilder: FormBuilder, private entretienService: EntretienService) {
    this.entretienForm = this.formBuilder.group({
      dateEntretien: [null, Validators.required],
      heure: [null, Validators.required],
      lien: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadEntretiens();
  }

  loadEntretiens(): void {
    this.entretienService.getAllEntretiens()
      .subscribe(data => {
        this.entretiens = data;
      }, error => {
        console.error("Erreur lors du chargement des entretiens :", error);
      });
  }
  onSubmit(): void {
    this.submitted = true;
    if (this.entretienForm.invalid) {
      return;
    }
    
    const entretien: EntretienModel = this.entretienForm.value;
    const idCandidature: number = 1;
    
    this.entretienService.affecterEntretienToCandidature(idCandidature, entretien)
      .subscribe(data => {
        console.log("Entretien affecté avec succès :", data);
        this.entretienService.sendEmail(entretien)
          .subscribe(() => {
            console.log("E-mail envoyé avec succès pour l'entretien :", entretien);
            this.loadEntretiens(); 
          }, error => {
            console.error("Erreur lors de l'envoi de l'e-mail pour l'entretien :", error);
          });
      }, error => {
        console.error("Erreur lors de l'affectation de l'entretien :", error);
      });
  }

  supprimerEntretien(idEntretien: number) {
    this.entretienService.deleteEntretienById(idEntretien).subscribe(
      () => {
        console.log('Entretien supprimée avec succès.');
        alert("Voulez-vous vraiment supprimer l'entretien ?")
        this.loadEntretiens();
      },
      (error) => {
        console.error('Erreur lors de la suppression de lEntretien:', error);
      }
    );
  }
}