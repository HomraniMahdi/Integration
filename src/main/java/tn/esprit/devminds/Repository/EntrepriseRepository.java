package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.Entreprise;

@Repository
public interface EntrepriseRepository extends CrudRepository<Entreprise,Long> {
}
