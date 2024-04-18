package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Offre;

import java.util.List;

public interface IOfferService {
    List<Offre> getAllOffres();
    Offre getOffreById(Long id);
    Offre addOffre(Offre offre);
    Offre updateOffre(Offre offre);
    void deleteOffre(Long id);

}
