package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository //component를 가지고있어서 자동으로 스프링 컨테이너에 등록된다.
public class MemberRepository { //엔티티를 찾아주는애 = dao

	@PersistenceContext //엔티티매니저를 주입해줌
	private EntityManager em;

	public Long save(Member member) {
		em.persist(member);
		return member.getId(); //왜 id를 가져오지? 커맨드와 쿼리를 분리해라
	}

	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
