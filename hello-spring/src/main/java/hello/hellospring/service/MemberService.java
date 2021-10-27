package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //중복회원이 있으면 안된다
        //optional을 바로 반환하는건 권장 no. 일단 안이쁨. 그래서 얘를 없애. 얘 반환이 옵셔널 //cmd opt v = 그냥 받아옴
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //optional이기 때문에 가능 한 점. 널이 아니라 어떤 값이 있으면 밑 동작을 하는것임
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        //role에 맞도록 네이밍 하세요
        return memberRepository.findAll(); //findall 반환타입 list엿으니까 그냥 return 하면됨
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
