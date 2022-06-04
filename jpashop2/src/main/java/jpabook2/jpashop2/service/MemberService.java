package jpabook2.jpashop2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) //기본적으로 트랜잭션안에서 해야함
@RequiredArgsConstructor
public class MemberService {

	// 필드로 이렇게 쓰는건 단점이 많어.. 일단 얘를 못바꾸잖아
	//@Autowired
	private final MemberRepository memberRepository;


	//단점: 런타임이 돌아가는 시점에 누가 setMemberRepository를 할 수도 있잖아..  그래서 얘도 안좋음 -> 그래서 생성자 인잭션을 씀
	// @Autowired
	// public void setMemberRepository(MemberRepository memberRepository) {
	// 	this.memberRepository = memberRepository;
	// }

	//이런 생성자 인젝션이 좋다 -> 생성자가 하나인 경우에는 autowired없어도 스프링이 알아서 Injection을 해준다
	//@Autowired
	// public MemberService(MemberRepository memberRepository) {
	// 	this.memberRepository = memberRepository;
	// }

	/**
	 * 회원검증
	 */
	@Transactional
	public Long join(Member member) {

		//중복 회원 검증
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		//성능은 이거보다, 해당 이름 cnt를 세서 그거 보다 큰지..확인하는게 더 좋겟죠?
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	//회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	//회원 단건 조회
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}