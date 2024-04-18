package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.EntretienEntreprise;

@Repository
public interface EntretienEntrepriseRepository extends CrudRepository<EntretienEntreprise,Long> {
}
