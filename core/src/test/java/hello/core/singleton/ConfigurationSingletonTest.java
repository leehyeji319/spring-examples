package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			AppConfig.class);

		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();
		//이게 과연 싱글톤이 되는지 ? 확인한다

		System.out.println("memberService -> memberRepository = " + memberRepository1);
		System.out.println("orderService -> memberRepository = " + memberRepository2);
		//와 두개 똑같음 ㄸㄸ ;;
		System.out.println("memberRepository = " + memberRepository);

		//검증
		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}

	@Test
	void configurationDeep() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class); //AnnotationConfigApplicationContext이걸 넘기면 얘도 빈으로 등록됨

		System.out.println("bean = " + bean.getClass());
		//class이름만 나와야하는데 뒤에 이상한게 붙어잇찌? $$EnhancerBySpringCGLIB$$47dd0ca5 띠용?이게뭐지?
	}
}
