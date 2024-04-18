package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.devminds.Entities.Entretien;

public interface IEntretientRepo extends JpaRepository<Entretien,Long> {
}
