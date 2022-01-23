package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

	@Test
	void prototypeBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			PrototypeBean.class); //여기 클래스를 넣어주면 이게 컴포넌트 스캔처럼 등록해줌
		System.out.println("find prototypeBean1");
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		System.out.println("find prototypeBean2");
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		System.out.println("prototypeBean1 = " + prototypeBean1);
		System.out.println("prototypeBean2 = " + prototypeBean2);
		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

		ac.close(); //close가 안뜨죠? ㄹㅇ 만들고 그냥 버린거임

		prototypeBean1.destory();
		prototypeBean2.destory(); //이런식으로 닫으려면 수작업으로 호출해서 닫아줘야한다
	}

	@Scope("prototype") //엥 여기 왜 컴포넌트가 없나요 ?
	static class PrototypeBean {
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init");
		}

		@PreDestroy
		public void destory() {
			System.out.println("PrototypeBean.destory");
		}
	}
}
