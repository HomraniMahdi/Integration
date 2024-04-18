package tn.esprit.devminds.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Evenement;
import tn.esprit.devminds.Repository.EventRepository;


import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImp implements IEvent {
    private EventRepository eventRepository;
    @Override
    public Evenement addEvent(Evenement event) {
        return eventRepository.save(event);
    }

    @Override
    public Evenement updateEvent(Evenement event) {
        eventRepository.findById(event.getIdEvenement()).orElse(null);
        return eventRepository.save(event);
    }

    @Override
    public Evenement getEventById(Long idEvenement) {
        return eventRepository.findById(idEvenement).orElse(null);
    }

    @Override
    public void deleteEvent(Long idEvenement) {
        eventRepository.deleteById(idEvenement);
    }
        // Méthode pour incrémenter le compteur de likes d'un événement
        @Override
        public void likeEvent(Long idEvenement) {
            Evenement event = eventRepository.findById(idEvenement).orElse(null);
            if (event != null) {
                event.setLikes(event.getLikes() + 1);
                eventRepository.save(event);
            }
        }

        // Méthode pour incrémenter le compteur de dislikes d'un événement
        @Override
        public void dislikeEvent(Long idEvenement) {
            Evenement event = eventRepository.findById(idEvenement).orElse(null);
            if (event != null) {
                event.setDislikes(event.getDislikes() + 1);
                eventRepository.save(event);
            }
        }




    @Override
    public List<Evenement> getAllEvent() {
        return eventRepository.findAll();
    }
    public void updateNbParticipantsAfterPayment(Long eventId) {
        // Fetch the event by ID
        Evenement event = eventRepository.findById(eventId).orElseThrow();

        // Increment nbparticipants
        event.setNbparticipants(event.getNbparticipants() + 1);

        // Save the updated event
        eventRepository.save(event);
    }
}
