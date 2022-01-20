package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
	// basePackages = "hello.core.member", //어디서부터 찾는지 지정할 수있음
	// basePackageClasses = AutoAppConfig.class,
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // @어노테이션 붙은거 다 찾아서 스프링 빈으로 등록해줌

public class AutoAppConfig {


	// //Overriding bean definition for bean 'memoryMemberRepository' 발생
	// @Bean(name = "memoryMemberRepository")
	// MemberRepository memberRepository() {
	// 	return new MemoryMemberRepository();
	// }
}
