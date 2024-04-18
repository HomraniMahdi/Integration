package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.Evenement;

@Repository
public interface EvenementRepository extends CrudRepository<Evenement,Long> {
}
