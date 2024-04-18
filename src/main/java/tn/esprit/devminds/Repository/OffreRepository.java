package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.Offre;
@Repository
public interface OffreRepository extends CrudRepository<Offre,Long> {
}
