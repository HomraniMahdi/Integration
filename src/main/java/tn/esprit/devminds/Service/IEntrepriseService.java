package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Entreprise;

import java.util.List;

public interface IEntrepriseService {
    List<Entreprise> getAllEntreprises();
    Entreprise getEntrepriseById(long id);
    Entreprise addEntreprise(Entreprise entreprise);
    Entreprise updateEntreprise(Entreprise entreprise);
    void deleteEntreprise(long id);
}
