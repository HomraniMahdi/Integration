import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { jsPDF } from 'jspdf';
import * as FileSaver from 'file-saver';
import 'jspdf-autotable';
import { EventModel } from 'src/app/Models/event-model';
import { EvenementService } from 'src/app/services/evenement.service';


@Component({
  selector: 'app-evenement',
  templateUrl: './evenement.component.html',
  styleUrls: ['./evenement.component.css']
})
export class EvenementComponent implements OnInit {
  listEvents: EventModel[] = [];
  selectedEvent: EventModel = new EventModel();
  isUpdate = false;
  searchTitle: string = ''; 
  

  constructor(private evenementService: EvenementService) { }

  ngOnInit(): void {
    this.loadEvents();
    this.exportToPDF();
  }

  loadEvents(): void {
    this.evenementService.getEvents().subscribe(data => {
      this.listEvents = data;
    });
  }

  saveEvent(): void {
    if (!this.isValidEvent()) {
      // Si le formulaire n'est pas valide, ne pas enregistrer
      return;
    }

    if (this.isUpdate) {
      this.evenementService.updateEvent(this.selectedEvent).subscribe(() => {
        this.loadEvents();
      });
    } else {
      this.evenementService.saveEvent(this.selectedEvent).subscribe(() => {
        this.loadEvents();
      });
    }
    this.selectedEvent = new EventModel();
    this.isUpdate = false;
  }

  deleteEvent(id: number): void {
    console.log('Deleting event with ID:', id);
    this.evenementService.deleteEvent(id).subscribe(() => {
      console.log('Event deleted successfully.');
      this.loadEvents();
    });
  }

  newEvent(): void {
    this.selectedEvent = new EventModel();
    this.isUpdate = false;
  }

  selectEvent(event: EventModel): void {
    this.selectedEvent = { ...event }; // Copy event object to avoid modifying original list
    this.isUpdate = true;
  }
  todayDate(): string {
    const today = new Date();
    return today.toISOString().split('T')[0]; // Convertit la date en format ISO (YYYY-MM-DD)
  }
  isValidEvent(): boolean {
    // Vérifier si tous les champs sont remplis et ont une longueur supérieure à 3 caractères
    return (
      !!this.selectedEvent.titre &&
      this.selectedEvent.titre.trim().length >= 3 &&
      !!this.selectedEvent.description &&
      this.selectedEvent.description.trim().length >= 3 &&
      !!this.selectedEvent.dateEvt &&
      !!this.selectedEvent.salle &&
      this.selectedEvent.salle.trim().length >= 3
    );
    }
    exportToPDF() {
      const doc = new jsPDF();
    
      // Ajouter la ligne de titre
      const title = 'Liste des événements';
      doc.setFontSize(16);
      doc.text(title, 10, 10);
    
      // Ajouter la ligne de noms de colonnes
      const columnNames = 'Titre, Description, Date, Salle';
      doc.setFontSize(12);
      doc.text(columnNames, 10, 20);
    
      const listItems = this.listEvents.map((event, index) => {
        return `${index + 1}. Titre: ${event.titre}, Description: ${event.description}, Date: ${event.dateEvt}, Salle: ${event.salle}`;
      });
    
      let yOffset = 30; // Position verticale de départ après les lignes de titre et de colonnes
    
      // Parcourir les éléments de la liste
      listItems.forEach(item => {
        doc.text(item, 10, yOffset); // Ajouter chaque élément à la position actuelle
        yOffset += 10; // Augmenter la position verticale pour le prochain élément
      });
    
      doc.save('liste_evenements.pdf');
    }
    searchEvents(): void {
      if (this.searchTitle.trim() === '') {
        // Si le champ de recherche est vide, charger tous les événements
        this.loadEvents();
      } else {
        // Filtrer la liste d'événements en fonction du titre
        this.listEvents = this.listEvents.filter(event =>
          event.titre.toLowerCase().includes(this.searchTitle.toLowerCase())
        );
      }
    }
    
    
    
    
    }