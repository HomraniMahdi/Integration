package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.devminds.Entities.StageRequest;

public interface StageRequestRepository extends JpaRepository<StageRequest, Long>{
}
