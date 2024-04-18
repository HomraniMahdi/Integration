package tn.esprit.devminds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.ConventionForm;
import tn.esprit.devminds.Entities.Etudiant;
import tn.esprit.devminds.Service.ConventionService;
import tn.esprit.devminds.Service.EtudiantService;

@RestController
@RequestMapping("/api/convention-form")
@CrossOrigin(origins = "http://localhost:4200")
public class ConventionController {

    @Autowired
    private ConventionService conventionFormService;

    @Autowired
    private EtudiantService etudiantService;  // Ajoutez le service pour récupérer les détails de l'étudiant

    @PostMapping("/submit")
    public ResponseEntity<?> submitConventionForm(@RequestBody ConventionForm conventionForm) {
        try {
            // Vérifier si le champ "Période de Stage" est renseigné
            if (conventionForm.getPeriodeStage() == null || conventionForm.getPeriodeStage().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le champ Période de Stage est obligatoire.");
            }

            // Récupérer l'étudiant à partir de la base de données en utilisant le cin
            Etudiant etudiant = etudiantService.getEtudiantByCin(conventionForm.getCin());

            if (etudiant != null) {
                // Remplir le formulaire de convention avec les informations de l'étudiant
                conventionForm.setNom(etudiant.getNom());
                conventionForm.setPrenom(etudiant.getPrenom());
                // Autres attributs à définir...

                // Processus de formulaire de convention
                conventionFormService.processConventionForm(conventionForm);

                return ResponseEntity.ok(conventionForm);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etudiant not found for CIN: " + conventionForm.getCin());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing convention form: " + e.getMessage());
        }
    }

    @GetMapping("/generate/{cin}")
    public ResponseEntity<?> getConventionForm(@PathVariable Integer cin) {
        try {
            // Récupérer l'étudiant à partir du service EtudiantService
            Etudiant etudiant = etudiantService.getEtudiantByCin(cin);

            if (etudiant != null) {
                // Générer le formulaire de convention en fonction des détails de l'étudiant
                ConventionForm conventionForm = new ConventionForm();
                conventionForm.setNom(etudiant.getNom());
                conventionForm.setPrenom(etudiant.getPrenom());
                conventionForm.setCin(etudiant.getCin());
                conventionForm.setNomEntreprise(etudiant.getNomEntreprise()); // Récupérer nomEntreprise depuis l'étudiant
                conventionForm.setPeriodeStage(etudiant.getPeriodeStage()); // Récupérer periodeStage depuis l'étudiant
                // Autres attributs à définir...

                return ResponseEntity.ok(conventionForm);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etudiant not found for CIN: " + cin);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating convention form: " + e.getMessage());
        }
    }

    @PutMapping("/validate/{cin}")
    public ResponseEntity<?> validerConvention(@PathVariable Integer cin) {
        try {
            // Logique pour valider la convention
            conventionFormService.validerConvention(cin);
            return ResponseEntity.noContent().build(); // Retourner un statut 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la validation de la convention : " + e.getMessage());
        }
    }

    @PutMapping("/reject/{cin}")
    public ResponseEntity<?> refuserConvention(@PathVariable Integer cin) {
        try {
            // Logique pour refuser la convention
            conventionFormService.refuserConvention(cin);
            return ResponseEntity.noContent().build(); // Retourner un statut 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du refus de la convention : " + e.getMessage());
        }
    }

}