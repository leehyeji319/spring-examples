package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import lombok.RequiredArgsConstructor;

public class SingletonWithPrototypeTest1 {

	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac =
			new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class); //둘다 자동 빈 등록됨

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1); //분명히 새로 만들어지는거니까 1이어야하는거 아닌가요?
	}

	@Scope("singleton") //일단 얘 클라이언트빈이 싱글톤이야 프로토타입빈을 만들어서 던져줘
	//@RequiredArgsConstructor
	static class ClientBean {
		//private final PrototypeBean prototypeBean; //생성자에 주입. 이미 주입이 되어있으므로 계속 같은걸 쓰는거야 x01

		@Autowired //field주입
		private Provider<PrototypeBean> prototypeBeanProvider;

		// @Autowired
		// public ClientBean(PrototypeBean prototypeBean) {
		// 	this.prototypeBean = prototypeBean;
		// }

		public int logic() { //무식하게 하나하나 해주면 싱글톤 빈이 아니라 프로토타입으로 매번 되겟지
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
			 int count = prototypeBean.getCount(); //inline cmd opt n
			 return count;
		}
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this); //this하면 현재 나의 참조값을 볼 수 잇겟죠 ?
		}

		@PreDestroy
		public void destroy() { //얘는 호출이 안되겟죠?
			System.out.println("PrototypeBean.destroy");
		}
	}
}
