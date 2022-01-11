package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //@Service를 붙이면 스프링이 스프링 컨테이너에 얘를 알아보고 딱 등록해
public class MemberService { //얘는 순수한 자바 코드

    private final MemberRepository memberRepository;
    //걍 외부에서 넣어줘
    //@Autowired
    @Autowired // autowired 가 잇으면 아 너는 멤머 리포지토리가 필요하구나 해서
    // 스프링 컨테이너에 있는 멤버 리포지토리를 뙇 넣어줌 memorymemberrepository 넣어줌 ㅋ
    public MemberService(MemberRepository memberRepository) { //new하는게 아니라 외부에서 넣어주죠? 이걸 dependency injection 이라고 함
        //직접 memberRepository를 new해서 생성하는게 아니라 외부에서 넣어주는것을 DI라고 한다.
        this.memberRepository = memberRepository; //즁요 !! 직접 내가 new해서 생성하는게 아니라 외부에서 넣어주도록 바꿔준다.
    }

    /**
     * 회원 가입
     */

    public Long join(Member member) {
        //같은이름이 있는 중복회원이 있으면 안된다
        // Optional<Member> result = memberRepository.findByName(member.getName());
        //optional을 바로 직접 꺼내서 반환하는건 권장 no. 일단 안이쁨. 그래서 얘를 없애. 얘 반환이 옵셔널 //cmd opt v = 그냥 받아옴
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //ctrl + T 하면 리팩토링 관련 단축키 나옴
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //optional이기 때문에 가능 한 점. 널이 아니라 어떤 값이 있으면 밑 동작을 하는것임
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        //role에 맞도록 네이밍 하세요
        return memberRepository.findAll(); //findall 반환타입 list엿으니까 그냥 return 하면됨
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
