package aos.projet.HumanRessourceBo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aos.projet.HumanRessourceBo.model.Role;
import aos.projet.HumanRessourceBo.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}