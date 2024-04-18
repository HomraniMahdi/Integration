package tn.esprit.devminds.Controller;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.Evenement;
import tn.esprit.devminds.Service.IEvent;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventRestController {
   private IEvent iEvent;
   @PostMapping("/addEvent")
    public Evenement addEvent(@RequestBody Evenement event){
        return iEvent.addEvent(event);

    }
    @PutMapping("/updateEvent")
    public Evenement updateEvent(@RequestBody Evenement event){
       return iEvent.updateEvent(event);

    }
    @GetMapping("/getEvent")
    public List<Evenement> getAllEvent(){
       return iEvent.getAllEvent();
    }
    @GetMapping("/getByIdEvent/{idEvenement}")
    public Evenement getEventById(@PathParam("idEvenement") Long idEvenement) {
        return iEvent.getEventById(idEvenement);
    }
    @DeleteMapping("/deleteEvent/{idEvenement}")
    public void deleteEvent(@PathVariable Long idEvenement) {
        iEvent.deleteEvent(idEvenement);
    }
    @PostMapping("/{idEvenement}/like")
    public ResponseEntity<?> likeEvent(@PathVariable Long idEvenement) {
        Evenement event = iEvent.getEventById(idEvenement);
        if (event != null) {
            event.setLikes(event.getLikes() + 1); // Augmentez le compteur de likes
            iEvent.updateEvent(event); // Mettez à jour l'événement dans la base de données
            return ResponseEntity.ok().body("{\"message\": \"L'événement a été liké avec succès !\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"L'événement avec l'ID " + idEvenement + " n'a pas été trouvé !\"}");
        }
    }

    @PostMapping("/{idEvenement}/dislike")
    public ResponseEntity<?> dislikeEvent(@PathVariable Long idEvenement) {
        Evenement event = iEvent.getEventById(idEvenement);
        if (event != null) {
            event.setDislikes(event.getDislikes() + 1); // Augmentez le compteur de dislikes
            iEvent.updateEvent(event); // Mettez à jour l'événement dans la base de données
            return ResponseEntity.ok().body("{\"message\": \"L'événement a été disliké avec succès !\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"L'événement avec l'ID " + idEvenement + " n'a pas été trouvé !\"}");
        }
    }
    @PostMapping("/{idEvenement}/share/facebook")
    public ResponseEntity<String> shareOnFacebook(@PathVariable Long idEvenement) {
        Evenement event = iEvent.getEventById(idEvenement);
        if (event != null) {
            // Générer le lien de partage pour Facebook avec les détails de l'événement
            String shareLink = "https://www.facebook.com/sharer/sharer.php?u=https://votre-site.com/evenements/" + event.getIdEvenement();
            return ResponseEntity.ok().body(shareLink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour partager un événement sur Instagram
    @PostMapping("/{idEvenement}/share/instagram")
    public ResponseEntity<String> shareOnInstagram(@PathVariable Long idEvenement) {
        Evenement event = iEvent.getEventById(idEvenement);
        if (event != null) {
            // Générer le lien de partage pour Instagram avec les détails de l'événement
            String shareLink = "https://www.instagram.com/stories/your-account/" + event.getIdEvenement();
            return ResponseEntity.ok().body(shareLink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{idEvenement}/share/email")
    public ResponseEntity<?> shareByEmail(@PathVariable Long idEvenement, @RequestParam String email) {
        // Récupérer les détails de l'événement et envoyer un email à l'adresse spécifiée
        // Utilisez un service d'envoi d'email pour envoyer l'email avec les détails de l'événement
        return ResponseEntity.ok().body("Email sent successfully to " + email);
    }

    private String generateEventUrl(Long idEvenement) {
        // Logique pour générer l'URL de l'événement
        return "https://example.com/events/" + idEvenement;
    }


}
