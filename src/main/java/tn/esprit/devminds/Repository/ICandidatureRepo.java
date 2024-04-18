package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Entities.Status;

import java.util.List;

public interface ICandidatureRepo extends JpaRepository<Candidature,Long> {
    @Query("SELECT c FROM Candidature c WHERE c.etudiant.idEtudiant = :etudiantId")
    List<Candidature> findByEtudiantId(Long etudiantId);
    List<Candidature> findByEmail(String email);
    @Query("SELECT c FROM Candidature c " +
            "WHERE (:nom IS NULL OR c.nom = :nom) " +
            "AND (:prenom IS NULL OR c.prenom = :prenom) " +
            "AND (:email IS NULL OR c.email = :email)")
    List<Candidature> searchCandidatures(@Param("nom") String nom,
                                         @Param("prenom") String prenom,
                                         @Param("email") String email);

    List<Candidature> findByStatus(Status status);
}
