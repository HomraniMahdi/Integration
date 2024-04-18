package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.devminds.Entities.PdfEntity;

import java.util.List;

public interface PdfRepository extends JpaRepository<PdfEntity, Long> {
    List<PdfEntity> findByEtudiantId(Long etudiantId);
}
