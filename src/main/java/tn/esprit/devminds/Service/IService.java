package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Entities.Entretien;
import tn.esprit.devminds.Entities.Offre;

public interface IService {
     Offre addOffre(Offre offre);
     Offre updateOffre(Offre offre);
     void deleteOffre(long id);
     Entretien addEntretien(Entretien entretien);
     Entretien updateEntretien(Entretien entretien);
     void deleteEntretien(long id);
     Candidature addCandidature(Candidature candidature);
     Candidature updateCandidature (Candidature candidature);
     void deleteCandidature (long id);

}
