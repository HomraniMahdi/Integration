package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Evenement;

import java.util.List;

public interface IEvent {
    public Evenement addEvent(Evenement event);

    public Evenement updateEvent(Evenement event);

    public Evenement getEventById(Long idEvenement);

    public void deleteEvent(Long idEvenement);

    public List<Evenement> getAllEvent();

    public void likeEvent(Long idEvenement);

    // Méthode pour incrémenter le compteur de dislikes d'un événement

    public void dislikeEvent(Long idEvenement);
}
