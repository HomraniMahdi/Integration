package tn.esprit.devminds.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Entities.Entretien;
import tn.esprit.devminds.Entities.Status;
import tn.esprit.devminds.Repository.ICandidatureRepo;
import tn.esprit.devminds.Repository.IEntretientRepo;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CandidatureServeImp implements ICandidature {
    private ICandidatureRepo iCandidatureRepo;

    @Override
    public Candidature addCandidature(Candidature c) {

        return iCandidatureRepo.save(c);
    }

    @Override
    public Candidature updateCandidature(Long idCandidature, Candidature c) {
        c.setIdCandidature(idCandidature);
        return iCandidatureRepo.save(c);
    }


    @Override
    public List<Candidature> getAllCandidature() {
        return iCandidatureRepo.findAll();
    }

    @Override
    public Candidature getCandidatureById(Long idCandidature) {
        return iCandidatureRepo.findById(idCandidature).orElse(null);
    }

    @Override
    public void deleteCandidture(long idCandidature) {
        iCandidatureRepo.deleteById(idCandidature);
    }

    @Override
    public List<Candidature> getCandidaturesByEtudiant(Long etudiantId) {
        return iCandidatureRepo.findByEtudiantId(etudiantId);
    }

    @Override
    public List<Candidature> getCandidaturesByEmail(String email) {
        return iCandidatureRepo.findByEmail(email);
    }


    @Override
    public List<Candidature> searchCandidatures(String nom, String prenom, String email) {
        return iCandidatureRepo.searchCandidatures(nom, prenom, email);
    }

    @Override
    public List<Candidature> getCandidaturesByFavorableStatus() {
        return iCandidatureRepo.findByStatus(Status.favorable);
    }



}
