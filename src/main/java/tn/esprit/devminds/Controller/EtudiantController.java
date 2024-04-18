package tn.esprit.devminds.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.Etudiant;
import tn.esprit.devminds.Service.EtudiantService;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;
    @PutMapping("/updateDemandeStage/{id}")
    public ResponseEntity<?> updateDemandeStage(@PathVariable Long id) {
        try {
            etudiantService.updateDemandeStage(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/etudiant")
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
        try {
            System.out.println("Received Request Payload: " + etudiant);
            Etudiant createdEtudiant = etudiantService.createEtudiant(etudiant);
            return new ResponseEntity<>(createdEtudiant, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        try {
            if (!id.equals(etudiant.getId())) {
                throw new IllegalArgumentException("Path variable id does not match etudiant id");
            }

            Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);
            return new ResponseEntity<>(updatedEtudiant, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        try {
            etudiantService.deleteEtudiant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        try {
            Etudiant etudiant = etudiantService.getEtudiantById(id);
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        try {
            List<Etudiant> etudiants = etudiantService.getAllEtudiants();
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
