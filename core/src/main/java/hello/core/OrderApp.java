package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
	public static void main(String[] args) {

		// AppConfig appConfig = new AppConfig();
		// MemberService memberService = appConfig.memberService();
		// OrderService orderService = appConfig.orderService();

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member); //넣어놔야 주문에서 찾아 쓸수잇음

		Order order = orderService.createOrder(memberId, "itemA", 10000);

		System.out.println("order = " + order); //order toString()으로 정의해놨음~
		//System.out.println("order.calculatePrice = " + order.calculationPrice()); //할인된 금액
	}
}
