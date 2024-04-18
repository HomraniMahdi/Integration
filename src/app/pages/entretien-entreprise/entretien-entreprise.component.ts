import { Component, OnInit } from '@angular/core';
import { Entretien } from 'src/app/Models/entretien.model';
import { EntretientEntrepriseService } from 'src/app/services/entretient-entreprise.service';
import { ExcelService } from 'src/app/services/excel.service';

@Component({
  selector: 'app-entretien-entreprise',
  templateUrl: './entretien-entreprise.component.html',
  styleUrls: ['./entretien-entreprise.component.css']
})
export class EntretienEntrepriseComponent {
  entretiens: Entretien[] = [];
  nouvelleEntretien: Entretien = new Entretien();
  entretienAModifier: Entretien | null = null;

  constructor(private entretienService: EntretientEntrepriseService, private excelService: ExcelService) { }

  ngOnInit(): void {
    this.getEntretiens();
  }

  exportToExcel(): void {
    this.excelService.exportToExcel(this.entretiens, 'entretiens');
  }

  getEntretiens(): void {
    this.entretienService.getAllEntretiens()
      .subscribe(entretiens => {
        this.entretiens = entretiens;
        console.log(entretiens); // Affiche les entretiens dans la console
      });
  }

  ajouterEntretien(): void {
    // Formater les dates au format requis (si nécessaire)
    this.nouvelleEntretien.dateEntretienR = new Date(this.nouvelleEntretien.dateEntretienR);
    this.nouvelleEntretien.dateEntretienT = new Date(this.nouvelleEntretien.dateEntretienT);

    // Appeler le service pour ajouter l'entretien
    this.entretienService.addEntretien(this.nouvelleEntretien)
      .subscribe(() => {
        this.getEntretiens(); // Rafraîchir la liste des entretiens après l'ajout
        this.nouvelleEntretien = new Entretien(); // Réinitialiser le formulaire
      });   
  }

  modifierEntretien(entretien: Entretien): void {
    this.entretienAModifier = { ...entretien };
  }

  updateEntretien(): void {
    if (this.entretienAModifier && this.entretienAModifier.idEntretien !== undefined) {
      this.entretienService.updateEntretien(this.entretienAModifier.idEntretien, this.entretienAModifier)
        .subscribe(() => {
          this.getEntretiens(); // Rafraîchir la liste des entretiens après la modification
          this.entretienAModifier = null; // Réinitialiser l'entretien à modifier
        });
    }
  }

  supprimerEntretien(idEntretien: number | undefined): void {
    if (idEntretien !== undefined) {
      this.entretienService.deleteEntretien(idEntretien)
        .subscribe(() => {
          this.getEntretiens(); // Rafraîchir la liste des entretiens après la suppression
        });
    } else {
      console.error('ID de l\'entretien non défini');
    }
  }
}

