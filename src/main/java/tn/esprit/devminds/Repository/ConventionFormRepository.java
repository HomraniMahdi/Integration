package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.devminds.Entities.ConventionForm;

public interface ConventionFormRepository extends JpaRepository<ConventionForm, Long> {
    ConventionForm findByCin(Integer cin);
}
