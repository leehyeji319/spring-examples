package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

class ApplicationContextBasicFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		// System.out.println("memberService = " + memberService);
		// System.out.println("memberService.getClass = " + memberService.getClass());
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("이름 없이 타입으로 조회")
	void findBeanByType() {
		MemberService memberService = ac.getBean(MemberService.class); //타입으로만 조회가능
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("구체 타입으로 조회")
	void findBeanByName2() {
		MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); //impl로 가능. 근데 이건 구현에 의존
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("빈 이름으로 조회 X")
	void findBeanByNameX() {
		//MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
		assertThrows(NoSuchBeanDefinitionException.class,
			() -> ac.getBean("xxxxx", MemberService.class)); //이 예외가 터지면 성공
	}


}
