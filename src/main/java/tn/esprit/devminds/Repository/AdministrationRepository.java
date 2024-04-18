package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.Administration;
@Repository
public interface AdministrationRepository extends CrudRepository<Administration,Long> {
}
