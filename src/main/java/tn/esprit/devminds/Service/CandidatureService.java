package tn.esprit.devminds.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Repository.CandidatureRepository;

import java.util.List;
@Service
public class CandidatureService implements ICandidatureService{
    @Autowired
    CandidatureRepository candidatureRepository;

    @Override
    public List<Candidature> getAllCandidatures() {
        return (List<Candidature>) candidatureRepository.findAll();
    }

    @Override
    public Candidature getCandidatureById(Long id) {
        return candidatureRepository.findById(id).orElse(null);
    }

    @Override
    public Candidature addCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    @Override
    public Candidature updateCandidature(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    @Override
    public void deleteCandidature(Long id) {
        candidatureRepository.deleteById(id);
    }
}
