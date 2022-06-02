package jpabook2.jpashop2;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook2.jpashop2.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;

	@Test
	@Transactional //EntityManager를 통한 데이터변경은 다 Transaction을 통해 이루어진다.
	//@Rollback(value = false)
	public void testMember() throws Exception {
		//given
		Member member = new Member();
		member.setUsername("memberA");

		//when
		Long savedId = memberRepository.save(member);
		Member findMember = memberRepository.find(savedId);

		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member); //True가 나옴
		//같은 영속성컨텍스트안에선 같은걸로 식별된다. 식별자가 같으면 같은 엔티티로 인식
		System.out.println("findMember == member: " + (findMember == member));
	}
}