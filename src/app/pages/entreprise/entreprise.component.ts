import { Component, OnInit } from '@angular/core';
import { Offre } from 'src/app/Models/offre.model';
import { OffreService } from 'src/app/services/offre.service';


@Component({
  selector: 'app-entreprise',
  templateUrl: './entreprise.component.html',
  styleUrls: ['./entreprise.component.css']
})
export class EntrepriseComponent implements OnInit {
  filteredOffres: Offre[] = [];
  searchText: string = '';
  offres: Offre[] = [];
  nouvelleOffre: Offre = new Offre();
  offreAModifier: Offre | null = null; // Déclaration explicite de la propriété
  notificationsCount: number = 0;



  constructor(private offreService: OffreService) { }

  ngOnInit(): void {
    this.getOffres();
  }

  getOffres(): void {
    this.offreService.getAllOffres()
      .subscribe(offres => this.offres = offres);
  }
  rechercherOffres(): void {
    if (this.searchText.trim() !== '') {
      this.offreService.searchOffres(this.searchText)
        .subscribe(offres => this.offres = offres);
    } else {
      this.getOffres(); // Recharger toutes les offres si le champ de recherche est vide
    }
  }

  ajouterOffre(): void {
    const nouvelleOffre = new Offre(1, 'Description de l\'offre', 'Titre de l\'offre', new Date());

    this.offreService.addOffre(this.nouvelleOffre)
      .subscribe(() => {
        this.getOffres();
        // Réinitialiser le modèle pour le formulaire
        this.nouvelleOffre = new Offre();
        this.notificationsCount++;

      });

  }


  modifierOffre(offre: Offre): void {
    // Crée une copie de l'offre pour éviter les modifications directes
    this.offreAModifier = { ...offre };
  }
  updateOffre(): void {
    if (this.offreAModifier && this.offreAModifier.idOffre !== undefined) {
      this.offreService.updateOffre(this.offreAModifier.idOffre, this.offreAModifier)
        .subscribe(() => {
          // Mettre à jour la liste des offres après modification
          this.getOffres();
          // Réinitialiser l'offre à modifier
          this.offreAModifier = null;
        });
    }
  }
    
  

    supprimerOffre(idOffre: number| undefined): void {
      if (idOffre !== undefined) {
        this.offreService.deleteOffre(idOffre)
          .subscribe(() => {
            this.getOffres();
          });
      } else {
        console.error('ID de l\'offre non défini');
      }
    }
    partagerOffre(offre: Offre) {
      const shareUrl = `https://www.linkedin.com/shareArticle?url=${encodeURIComponent(window.location.href)}`;
      window.open(shareUrl, '_blank');
  }
  
    
}
