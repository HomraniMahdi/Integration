package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Candidature;

import java.util.List;

public interface ICandidatureService {

    List<Candidature> getAllCandidatures();
    Candidature getCandidatureById(Long id);
    Candidature addCandidature(Candidature candidature);
    Candidature updateCandidature(Candidature candidature);
    void deleteCandidature(Long id);


}
