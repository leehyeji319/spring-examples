package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
//인터페이스가 인터페이스 받을땐 implements
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

	//JPQL select m from Member m where m.name = ?
	@Override
	Optional<Member> findByName(String name);
}