package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

	public static void main(String[] args) {
		// AppConfig appConfig = new AppConfig();
		// MemberService memberService = appConfig.memberService(); //appConfing에서 멤버서비스 준다.

		//applicationContext가 모든걸 관리해준다. 객체들(Bean)
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //parameter appconfig
		MemberService memberService = applicationContext.getBean("memberService",
			MemberService.class);//꺼낼이름 이름적고, 반환 타입
		//join하기
		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);
		System.out.println("new Member = " + member.getName());
		System.out.println("find Member = " + findMember.getName());
	}
}
