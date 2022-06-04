package jpabook2.jpashop2.service;

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

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	//@Autowired EntityManager em;

	@Test
	//@Rollback(value = false)
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setName("kim");

		//when
		Long savedId = memberService.join(member);

		//then
		//em.flush(); //쿼리를 보고싶으면 이거해
		Assert.assertEquals(member, memberRepository.findOne(savedId));
	}

	@Test(expected = IllegalStateException.class) //저기 fail에서 터져서 나간애가 IllegalStateException이면 됩니다.
	public void 중복_회원_예약() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim1");

		Member member2 = new Member();
		member2.setName("kim1");

		//when
		memberService.join(member1);
		memberService.join(member2); //예외가 발생해야한다 !!

		//then
		//코드가 돌다가 여기 오면 안되는거임. 여기 오면 뭔가,,something's wrong; ;;
		fail("예외가 발생해야한다."); //밑으로 로직이 나간다면,
	}
}