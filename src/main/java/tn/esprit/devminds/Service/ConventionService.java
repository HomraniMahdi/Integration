package tn.esprit.devminds.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.ConventionForm;
import tn.esprit.devminds.Entities.Etudiant;
import tn.esprit.devminds.Repository.ConventionFormRepository;


@Service
@Slf4j
public class ConventionService {

    @Autowired
    private ConventionFormRepository conventionFormRepository;

    @Autowired
    private EtudiantService etudiantService;

    public ConventionForm generateConventionFormByCin(Integer cin) {
        Etudiant etudiant = etudiantService.getEtudiantByCin(cin);
        if (etudiant == null) {
            throw new EntityNotFoundException("Étudiant non trouvé avec le CIN : " + cin);
        }

        ConventionForm conventionForm = new ConventionForm();
        conventionForm.setNomEntreprise(""); // Initialisez comme nécessaire
        conventionForm.setPeriodeStage(""); // Initialisez comme nécessaire
        conventionForm.setNom(etudiant.getNom()); // Utilisez le nom de l'étudiant
        conventionForm.setPrenom(etudiant.getPrenom()); // Utilisez le prénom de l'étudiant
        // Ajoutez d'autres champs si nécessaire

        return conventionForm;
    }

    public void processConventionForm(ConventionForm conventionForm) {
        // Récupérer l'étudiant par CIN
        Etudiant etudiant = etudiantService.getEtudiantByCin(conventionForm.getCin());

        // Vérifier si l'étudiant existe
        if (etudiant == null) {
            throw new EntityNotFoundException("Aucun étudiant trouvé avec le CIN : " + conventionForm.getCin());
        }

        // Mettre à jour les champs dans l'entité Etudiant
        etudiant.setNomEntreprise(conventionForm.getNomEntreprise());
        etudiant.setPeriodeStage(conventionForm.getPeriodeStage());

        conventionForm.setRefuse(false); // définir la valeur par défaut ou celle que vous voulez

        try {
            conventionFormRepository.save(conventionForm);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du formulaire de convention dans la base de données.", e);
        }
    }
    public void validerConvention(Integer cin) {
        Etudiant etudiant = etudiantService.getEtudiantByCin(cin);
        if (etudiant != null) {
            etudiant.setValide(true);
            etudiantService.updateEtudiant(etudiant);
        }
    }

    public void refuserConvention(Integer cin) {
        Etudiant etudiant = etudiantService.getEtudiantByCin(cin);
        if (etudiant != null) {
            etudiant.setRefuse(true);
            etudiantService.updateEtudiant(etudiant);
        }
    }
}
