package recorder.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import recorder.backend.domain.user.Role;
import recorder.backend.domain.user.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName roleName);
}
