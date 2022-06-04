package jpabook2.jpashop2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jpabook2.jpashop2.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository //spring bean으로 등록을 해줌
@RequiredArgsConstructor
public class MemberRepository {

	//@PersistenceContext //스프링 엔티티매니저를 만들어서 injection해줌
	//spring data jpa를 쓰면 @Autowired를 해도 됨
	//@Autowired
	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() { //JPQL Member는 테이블이 아니라 Entity를 가르킨다
		return em.createQuery("select m from Member m", Member.class)
			.getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name",
			Member.class)
			.setParameter("name", name)
			.getResultList();
	}
}
