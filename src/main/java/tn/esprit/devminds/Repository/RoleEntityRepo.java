package tn.esprit.devminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.RoleEntity;

@Repository
public interface RoleEntityRepo extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByRoleName(String roleName);
}
