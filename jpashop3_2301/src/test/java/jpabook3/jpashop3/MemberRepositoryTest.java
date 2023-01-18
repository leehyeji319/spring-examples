package jpabook3.jpashop3;

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

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional //트랜잭션이 테스트 위에 있으면 롤백을 자동으로 해줌 //spring이 제공하는 트랜잭션 쓰세요. 쓸수잇는 옵션이 더 많음
	@Rollback(false)
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
		Assertions.assertThat(findMember).isEqualTo(member);

	}

}