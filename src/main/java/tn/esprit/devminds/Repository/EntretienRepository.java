package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.Entretien;

@Repository
public interface EntretienRepository extends CrudRepository<Entretien,Long> {
}
