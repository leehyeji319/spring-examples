package hello.core.member;

public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();//구현 객체 선택해주기
	@Override
	public void join(Member member) {
		memberRepository.save(member); //다형성으로 new 한게 호출이 되겟죠?
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
