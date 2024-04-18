package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.devminds.Entities.Offre;

public interface IOffreRepo extends JpaRepository<Offre,Long> {
}
