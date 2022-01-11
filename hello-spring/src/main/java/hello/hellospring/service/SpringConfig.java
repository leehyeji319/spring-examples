package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration //spring이 뜰때 이 컨피그레이션을 읽고 어?? 빈에 등록하라는 뜻이네
public class SpringConfig {

    @Bean //멤버서비스 이 로직을 호출을해서 스프링 빈에 등록을 해줌
    public MemberService memberService() {
        return new MemberService(memberRepository()); //생성자에서 뭘 넣어줘야돼 멤버리포지토리넣어줘야해
    }

    @Bean //이것도 스프링 빈에 등록을 함 멤버 서비스에 넣어줌
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

