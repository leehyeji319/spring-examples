package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional 자바 8 기능
    Optional<Member> findByName(String name);
    List<Member> findAll();

    //이제 구현체만들어야지
}
