package hello.hellospring.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; //static이 되엇음

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //method가 끝날 때마다 repository 지워주는 callback method
    @AfterEach
    public void afterEach(){
        //끝날때마다 호출이 된다.
        repository.clearStore(); //지운다
    }

    @Test //Junit annotation에 test
    public void save(){
        Member member = new Member(); //저장한거랑
        member.setName("spring");

        repository.save(member); //저장하고 나면 아이디 세팅되는거임

        Member result = repository.findById(member.getId()).get(); //optional에서 꺼낼땐 get 으로 빠로 꺼낼 수 있다. 디비에서 꺼낸거랑
        //System.out.println("result = " + (result == member)); //같은지 피교
        //하지만 내가 글자를 볼 수 없다면?
        //Assertions.assertEquals(member, result;
        assertThat(member).isEqualTo(result); //asssertThat에서 result랑 똑같아
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

        //왜 에러가 날까 ?
//        test 순서가 지들 맘대로 되는데, findAll 이 먼저 되니까 저장되어벌인것임 그래서 findbyname이 안되는거야
//                그래서 테스트를 하나 끝나고 나면  데이터를 깔끔하게 clear를 해주ㅕ야돼
    }


    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); //result 결과가 나오겟지

        assertThat(result.size()).isEqualTo(2); //사이즈는 두개가 나와야해

    }
}
