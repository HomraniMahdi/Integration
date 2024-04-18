package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.devminds.Entities.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity,Long> {
    FileEntity findByFilename(String filename);

}
