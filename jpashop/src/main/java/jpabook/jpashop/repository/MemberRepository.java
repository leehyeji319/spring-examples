package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository //자동 스프링 빈 등록
@RequiredArgsConstructor
public class MemberRepository {

	//@PersistenceContext //이 어노테이션이 있으면 jpa entity manager를 지가 주입해줌
	//@Autowired
	private final EntityManager em; //스프링이 엔티티매니저를 만들어서 주입 인젝션해줌

	//@RequiredArgsConstructor로 생략 가능
	// public MemberRepository(EntityManager em) {
	// 	this.em = em;
	// }

	//저장 로직
	public void save(Member member) {
		em.persist(member); //jpa가 저장
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {// 멤버를 리스트로
		return em.createQuery("select m from Member m", Member.class) //jpql query, 조회 타입
			.getResultList();
	}

	public List<Member> findByName(String name) { //이름으로 조회
		return em.createQuery("select m from Member m where m.name = :name", Member.class) // :name -> parameter를 바인딩
			.setParameter("name", name)
			.getResultList();
	}
}
