package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.devminds.Entities.Etudiant;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {

    List<Etudiant> findByNom(String nom);

    Optional<Etudiant> findByEmail(String email);

    Etudiant findByCin(Integer cin);
}
