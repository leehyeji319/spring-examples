package jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) //data 변경하는것은 transaction이 꼭 있어야한다. spring의 transcational 을 쓰셈
//@AllArgsConstructor -> 생성자를 만들어줌(lombok)
@RequiredArgsConstructor //final 있는 필드만 가지고 생성자를 만들어줌
public class MemberService {

	//@Autowired //인젝션 이런건 단점이 많음 못바꾸는것 등 ..
	private final MemberRepository memberRepository; //final을 붙이면 컴파일 시점에 확인 가능하니 붙여주면 좋다

	// //@Autowired //생성자 injection -> 한 번 생성할때 완성이 되기때문에 중간에 set으로 못바꾼다. 테케할때도 좋음. 생성자 하나인경우 오토와이어드 자동으로 해줌
	// public MemberService(MemberRepository memberRepository) {
	// 	this.memberRepository = memberRepository;
	// }

	// @Autowired //setter injection 장점: 테스트 코드같은거 할때 직접 주입 가능, 단점: 내가 실행할때 남이 바꿀수도잇잖아 ..
	// public void setMemberRepository(MemberRepository memberRepository) {
	// 	this.memberRepository = memberRepository;
	// }

	/*
	* 회원 가입
	* */
	@Transactional(readOnly = false) //얘는 읽기가 아니니까 transactional default 설정
	public Long join(Member member) {
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return  member.getId(); //id 반환
	}

	private void validateDuplicateMember(Member member) { //Members를 세서 0보다 크면 문제가 있다 이런식으로 해도 ㄱㅊ
		//EXCEPTION
		List<Member> findMembers = memberRepository.findByName(member.getName());//찾아보자
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}

	}

	//회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}

	@Transactional //변경 감지를 사용해라
	public void update(Long id, String name) {
		Member member = memberRepository.findOne(id);
		member.setName(name);
	}
}
