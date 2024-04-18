import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EventModel } from 'src/app/Models/event-model';
import { EvenementService } from 'src/app/services/evenement.service';


@Component({
  selector: 'app-evenement-user',
  templateUrl: './evenement-user.component.html',
  styleUrls: ['./evenement-user.component.css']
})
export class EvenementUserComponent {
  events: EventModel[] = [];
  message: string = ''; // Variable pour stocker le message
  showShareMenu: boolean = false; // Déclarez la propriété showShareMenu ici

  constructor(private evenementService: EvenementService,private router:Router) { }

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.evenementService.getEvents().subscribe(data => {
      this.events = data.map(event => ({ ...event, isPulsating: false }));
    });
  }
  

  participer(event: EventModel): void {
    event.isPulsating = true;
    event.pulseButton = 'participer';
   
    this.router.navigate(['/PartciperEvent']);
  
  }
  like(event: EventModel): void {
    event.isPulsating = true;
    event.pulseButton = 'like';
    this.evenementService.likeEvent(event.idEvenement).subscribe(() => {
      console.log('Like pour l\'événement : ', event);
      event.message = 'Like ajouté avec succès !'; // Mettre à jour le message de l'événement
      setTimeout(() => {
        event.message = ''; // Effacer le message après quelques secondes
      }, 3000); // Temps en millisecondes
    });
  }
  
  dislike(event: EventModel): void {
    event.isPulsating = true;
    event.pulseButton = 'dislike';
    this.evenementService.dislikeEvent(event.idEvenement).subscribe(() => {
      console.log('Dislike pour l\'événement : ', event);
      event.message = 'Dislike ajouté avec succès !'; // Mettre à jour le message de l'événement
      setTimeout(() => {
        event.message = ''; // Effacer le message après quelques secondes
      }, 3000); // Temps en millisecondes
    });
  }
  shareOnFacebook(event: EventModel): void {
    // Construction de l'URL de partage
    const shareUrl = `https://www.facebook.com/sharer/sharer.php?u=${encodeURIComponent(event.url)}`;
    // Ouvrir la fenêtre de partage Facebook dans une nouvelle fenêtre
    window.open(shareUrl, '_blank');
  }

  shareOnInstagram(event: EventModel): void {
    // Construction de l'URL de partage
    const shareUrl = `https://www.instagram.com/share?url=${encodeURIComponent(event.url)}`;
    // Ouvrir la fenêtre de partage Instagram dans une nouvelle fenêtre
    window.open(shareUrl, '_blank');
  }
  toggleShareMenu(): void {
    this.showShareMenu = !this.showShareMenu;
  }
  
  

}
