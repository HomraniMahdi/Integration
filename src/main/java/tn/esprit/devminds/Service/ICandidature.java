package tn.esprit.devminds.Service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Entities.Entretien;

import java.io.IOException;
import java.util.List;

public interface ICandidature {
public Candidature addCandidature(Candidature c);
public Candidature updateCandidature(Long idCandidature, Candidature c);
public List<Candidature> getAllCandidature();
public Candidature getCandidatureById(Long idCandidature);
public void deleteCandidture(long idCandidature);
List<Candidature> getCandidaturesByEtudiant(Long etudiantId);
List<Candidature> getCandidaturesByEmail(String email);
List<Candidature> searchCandidatures(String nom, String prenom, String email);
 List<Candidature> getCandidaturesByFavorableStatus();

}
