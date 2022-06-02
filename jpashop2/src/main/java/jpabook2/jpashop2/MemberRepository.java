package jpabook2.jpashop2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

	//jpa를 쓰니까 entitymanager잇어야지
	@PersistenceContext
	private EntityManager em;

	public Long save(Member member) {
		em.persist(member);
		return member.getId(); //why id를 반환? 그냥 멤버 리턴받으면 되잖아.
	}

	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
