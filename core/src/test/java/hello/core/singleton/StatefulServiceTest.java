package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
	@Test
	void statefulServiceSingleton() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

		//ThreadA: A사용자가 만원 주문
		statefulService1.order("userA", 10000);
		//ThreadB : B사용자 20000원 주문
		statefulService2.order("userB", 20000);

		//ThreadA : 사용자A 주문 금액 조회
		int price = statefulService1.getPrice();
		System.out.println("price = " + price);
		//중간에 사용자B가 바꿔서 20000원이 되어벌임 ㅠㅠ

		assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}