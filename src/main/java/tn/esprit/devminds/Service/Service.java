package tn.esprit.devminds.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Entities.Entretien;
import tn.esprit.devminds.Entities.Offre;
import tn.esprit.devminds.Repository.CandidatureRepository;
import tn.esprit.devminds.Repository.EntrepriseRepository;
import tn.esprit.devminds.Repository.EntretienRepository;
import tn.esprit.devminds.Repository.OffreRepository;

@org.springframework.stereotype.Service
@AllArgsConstructor
@Slf4j
public class Service implements IService {
    OffreRepository offreRepository;
    EntretienRepository entretienRepository;
    CandidatureRepository candidatureRepository;
    EntrepriseRepository entrepriseRepository;
    public Offre getOffreById(Long id) {
        return offreRepository.findById(id).orElse(null);
    }
        public Offre addOffre(Offre offre) {
            return offreRepository.save(offre);
        }

    @Override
    public Offre updateOffre(Offre offre) {
        return offreRepository.save(offre);
    }
    @Override
    public void deleteOffre(long id) {
        offreRepository.deleteById(id);
    }
    @Override
    public Entretien addEntretien(Entretien entretien) {
        return entretienRepository.save(entretien);}
    @Override
    public Entretien updateEntretien(Entretien entretien) {
        return entretienRepository.save(entretien);
    }
    @Override
    public void deleteEntretien(long id) {
        entretienRepository.deleteById(id);
    }
    public Candidature addCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);}
    @Override
    public Candidature updateCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }
    @Override
    public void deleteCandidature(long id) {
        candidatureRepository.deleteById(id);
    }


}

