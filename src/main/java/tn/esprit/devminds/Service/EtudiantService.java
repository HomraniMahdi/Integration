package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Etudiant;

import java.util.List;

public interface EtudiantService {
    void updateDemandeStage(Long id);

    Etudiant createEtudiant(Etudiant etudiant);


    Etudiant updateEtudiant(Etudiant etudiant);


    void deleteEtudiant(Long id);


    Etudiant getEtudiantById(Long id);


    List<Etudiant> getAllEtudiants();


    Etudiant getEtudiantByCin(Integer cin);


}
