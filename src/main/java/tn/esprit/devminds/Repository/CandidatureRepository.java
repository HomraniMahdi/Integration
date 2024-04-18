package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.Candidature;

@Repository
public interface CandidatureRepository extends CrudRepository<Candidature,Long> {
}
