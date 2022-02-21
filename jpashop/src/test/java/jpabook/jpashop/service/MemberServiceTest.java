package jpabook.jpashop.service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //트랜잭션을 걸고 실행했다가 기본으로 롤백함
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	//롤백이지만 디비에 쿼리 날리는거 보고싶어 !!
	@Autowired EntityManager em;

	@Test
	//@Rollback(value = false)
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setName("kim");

		//when
		Long savedId = memberService.join(member);

		//then
		//em.flush(); //롤백이지만 디비에 쿼리 날리는거 보고싶어 !!
		assertEquals(member, memberRepository.findOne(savedId));
	}

	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");

		//when
		memberService.join(member1);
		memberService.join(member2); //예외가 발생해야한다 ! 여기서 이미 예외가 터지고 빠져나가서 밑에 fail엔 도달을 못하게

		//then
		fail("예외가 발생해야 한다."); //Assert에 있는 fail
	}
}