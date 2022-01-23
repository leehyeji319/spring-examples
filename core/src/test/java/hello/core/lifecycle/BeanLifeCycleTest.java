package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

	@Test
	public void lifeCycleTest() {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close(); // close()를 지원하는게 ConfigurableApplicationContext에 잇음
	}

	@Configuration
	static class LifeCycleConfig {

		@Bean
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient(); //객체를 생성한 다음에 필요한 값을 세터로 넣어줫어요 ..
			networkClient.setUrl("http://hello-spring.dev"); //넣어주고나서 초기화를 호출하고싶어
			return networkClient; //호출된 결과물이 스프링 빈에 등록이 되겟죠?
		}
	}
}
