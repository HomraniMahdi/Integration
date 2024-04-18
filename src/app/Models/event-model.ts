export class EventModel
{
    "idEvenement": number;
    "titre": string;
     "description": string;
     "dateEvt": Date;
     "salle": string;
     "like": number;
    "dislike": number;
    message?: string;
   "isPulsating": boolean = false; // Ajoutez la propriété isPulsating
   pulseButton: string = '';
   "url": string; // Ajoutez cette propriété pour stocker l'URL de l'événement

}