package tn.esprit.devminds.Service;


import lombok.AllArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Etudiant;
import tn.esprit.devminds.Repository.EtudiantRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;


    @Override
    public void updateDemandeStage(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant not found"));
        // Mettez à jour l'état de la demande de stage de l'étudiant
        etudiant.setDemandeStageEffectuee(true); // Par exemple
        etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant createEtudiant(Etudiant etudiant) {
        validateEtudiant(etudiant);
        checkIfEmailExists(etudiant.getEmail());
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
        validateEtudiant(etudiant);
        checkIfEtudiantExists(etudiant.getId());
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void deleteEtudiant(Long id) {
        checkIfEtudiantExists(id);
        etudiantRepository.deleteById(id);

    }

    @Override
    public Etudiant getEtudiantById(Long id) {
        checkIfEtudiantExists(id);
        return etudiantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return ListUtils.emptyIfNull(
                StreamSupport.stream(etudiantRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Etudiant getEtudiantByCin(Integer cin) {
        return etudiantRepository.findByCin(cin);
    }



    private void validateEtudiant(Etudiant etudiant) {
        if (etudiant == null || etudiant.getNom().isEmpty() || etudiant.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Invalid Etudiant data");
        }
    }

    private void checkIfEtudiantExists(Long id) {
        if (id == null || !etudiantRepository.existsById(id)) {
            throw new IllegalArgumentException("Etudiant does not exist");
        }
    }

    private void checkIfEmailExists(String email) {
        etudiantRepository.findByEmail(email)
                .ifPresent(existingEtudiant -> {
                    throw new IllegalArgumentException("Etudiant email already exists");
                });
    }

}
