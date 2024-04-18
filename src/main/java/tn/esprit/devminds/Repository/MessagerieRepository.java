package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.messagerie;

@Repository
public interface MessagerieRepository extends CrudRepository<messagerie,Long> {
}
