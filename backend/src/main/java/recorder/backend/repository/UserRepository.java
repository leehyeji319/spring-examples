package recorder.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import recorder.backend.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByDomain(String domain);

	List<User> findByIdIn(List<Long> userIds);

	Boolean existsByEmail(String email);

	Boolean existsByDomain(String domain);
}
