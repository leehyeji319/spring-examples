package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService = new MemberService();
    // clear를 해주고 싶은데 memberservice바껭없잖아
    //memberrepository 가져와야됨
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        //끝날때마다 호출이 된다.
        memberRepository.clearStore(); //돌때마다 db의 값을 날려줌
    }


    @Test
    void 회원가입() { //test는 그냥 한글로도 많이 적음
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findmember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findmember.getName());
        // 이거는 너무 단순해

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//예네가 터져야됨 이 예외가!!

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {
            memberService.join(member2);
            //만약 위가 실행되고 익셉션 안터지고 내려가면 실패지?
            fail();

        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }  //예외때문에 try catch 하는게 좀 애매해 그래서 문법을 제공합니다
*/


        // 여기서 중복 예외가 터져야됨 validate에서 걸려야됨

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}