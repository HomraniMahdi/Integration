package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.devminds.Entities.JournalStage;

import java.util.Optional;

public interface JournalStageRepository extends JpaRepository<JournalStage, Long> {
    @Query("SELECT js FROM JournalStage js WHERE js.cin = :cin")
    Optional<JournalStage> findByCin(@Param("cin") Long cin);

}
