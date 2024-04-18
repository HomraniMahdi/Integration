package tn.esprit.devminds.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.DocStage;
@Repository
public interface DocStageRepository extends CrudRepository<DocStage,Long> {
}
