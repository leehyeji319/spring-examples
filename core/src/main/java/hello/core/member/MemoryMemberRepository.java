package hello.core.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component //memoryMemberRepository 소문자로 바껴서 빈에 이렇게 등록된다고 햇죠 ?
public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store = new HashMap<>(); //hashmap은 동시성 이슈가 잇을수도잇음

	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}
}
