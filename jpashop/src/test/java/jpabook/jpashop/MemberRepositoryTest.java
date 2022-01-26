package jpabook.jpashop;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;

	@Test
	@Transactional //트랜잭션이잇어야 동작을 하죠 ~~ 꼭 있어야해
	//Transactional	이 테스트에 있으면 디비를 롤백해줘.
	//@Rollback(value = false)
	public void testMember() throws Exception {
		//given
		Member member = new Member();
		member.setUsername("memberA");

		//when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);
		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member); //저장한거랑 조회한거랑 같을까요?
		//True가 되어야함 같은 트랜잭션에서 저장하고 조회하면 같은 영속성 컨텍스트에서 같은 엔티티로 식별
		System.out.println("findMember == member: " + (findMember == member));
	}
}