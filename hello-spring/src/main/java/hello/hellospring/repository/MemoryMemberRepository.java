package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    //Save 를 할 때 map 을 쓸게요
    private static Map<Long, Member> store = new HashMap<>();
    // 시퀀스 생성, 0 1 2 키값을 생성해줌. long에서 하는 것보다 동시성 문제를 고려해서 해야하는데 예시니까
    private static long sequence = 0L;


    @Override
    public Member save(Member member) { //이미 이름은 넘어온 상태
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member; //저장된 공간을 반환을 해주고요
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(store.get(id)); //null 일수 도 잇지. 널이 반환될 가능성이 잇으면 옵셔널이라고 감싼다 client에서 처리하도록
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))//람다
                .findAny();
        //파라미터로 넘어온 네임과 여기 네임이 같은지 확인
        //같은 경우에만 필터가 된다 넘어감
         //java8 lambda

    }

    @Override
    public List<Member> findAll() { //실무에선 list 많이 씀
        //map 이지만 반환은 list
        return new ArrayList<>(store.values()); //store에 있는 member들이 쭉 반환이 된다.
    }

    public void clearStore() {
        store.clear(); //싹 비우기
    }
}
