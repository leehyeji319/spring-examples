package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {  //iter tab하면 for문 자동 완성
			Object bean = ac.getBean(beanDefinitionName);
			System.out.println("name = " + beanDefinitionName + "object = " + bean);
		}
	}

	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {  //iter tab하면 for문 자동 완성
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//metadata정보

			//ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
			//ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
			//우리가 등록한 빈 다섯개만 출력된다.
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name = " + beanDefinitionName + "object = " + bean);
			}
		}
	}
}
