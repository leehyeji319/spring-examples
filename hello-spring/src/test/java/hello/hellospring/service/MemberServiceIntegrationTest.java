package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
//스프링 통합테스트
//ctrl + R 이전 실행 그대로 실행해줌
@SpringBootTest
@Transactional //얘를 테스트케이스에 달면 테스트를 실행할대 트랜잭션을 먼저 실행하고 디비에 인서트 쿼리 다 실행한다음에, 테스트가 끝나면 롤백을 해준다.
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService; //여기를 비포이치로 인해서 변경
    // clear를 해주고 싶은데 memberservice바껭없잖아
    //memberrepository 가져와야됨
    @Autowired MemberRepository memberRepository; //여기 넣어놓고

    @Test
    void 회원가입() { //test는 그냥 한글로도 많이 적음
        //given //이 데이터를 기반으로
        Member member = new Member();
        member.setName("spring");

        //when //이걸 검증하는구나
        Long saveId = memberService.join(member);

        //then //여기서 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
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
        memberService.join(member1); //여기선 문제가 없겟지 두번째 조인검증할때 validate에 걸려서 예외가 터져야됨
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//예네가 터져야됨 이 예외가!!

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {
            memberService.join(member2);
            //만약 위가 실행되고 익셉션 안터지고 내려가면 실패지?
            fail(); //예외가 발생해야합니다.

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