import { Component, OnInit } from '@angular/core';
import { CandidatureService } from 'src/app/services/candidature.service';
import { Router } from '@angular/router';
import { CandidatureModel } from 'src/app/Models/CondidatureModel';


@Component({
  selector: 'app-candidature',
  templateUrl: './candidature.component.html',
  styleUrls: ['./candidature.component.css']
})
export class CandidatureComponent implements OnInit {
  listeCandidatures: CandidatureModel[] = [];
  candidaturesEpingles: CandidatureModel[] = [];
  filteredCandidatures: CandidatureModel[] = [];
  recherche: string = '';
  afficherFavorable: boolean = false;

  constructor(private candidatureService: CandidatureService, private router: Router) { }

  ngOnInit(): void {
    this.loadCandidatures();
  }

  loadCandidatures() {
    this.candidatureService.getListCandidature().subscribe(
      (data: CandidatureModel[]) => {
        this.listeCandidatures = data;
        this.filteredCandidatures = this.listeCandidatures;
        this.loadCandidaturesEpingles();
      },
      (error) => {
        console.error('Erreur lors du chargement des candidatures :', error);
      }
    );
  }

  loadCandidaturesEpingles() {
    this.candidatureService.getCandidaturesByFavorableStatus().subscribe(
      (data: CandidatureModel[]) => {
        this.candidaturesEpingles = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des candidatures épinglées :', error);
      }
    );
  }

  onFavorableSearchChange(): void {
    if (this.afficherFavorable) {
      this.filteredCandidatures = this.candidaturesEpingles;
    } else {
      this.filteredCandidatures = this.listeCandidatures;
    }
  }

  onRechercheChange(): void {
    this.filteredCandidatures = this.listeCandidatures.filter(candidature =>
      candidature.nom.toLowerCase().includes(this.recherche.toLowerCase())
    );
    this.afficherFavorable = false;
  }

  ajouterCandidature() {
    this.router.navigate(['/addCandidature']);
  }

  modifierCandidature(idCandidature: number) {
    console.log('ID Candidature:', idCandidature);
    this.router.navigate(['modify-cand', idCandidature]);
  }

  supprimerCandidature(idCandidature: number) {
    this.candidatureService.deleteCandidatureById(idCandidature).subscribe(
      () => {
        console.log('Candidature supprimée avec succès.');
        alert("Voulez-vous vraiment supprimer cette candidature ?");
        this.loadCandidatures();
      },
      (error) => {
        console.error('Erreur lors de la suppression de la candidature :', error);
      }
    );
  }
}
