package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService{

	//final이 되어잇으면 기본이든 생성자를 통해서 할당이 되어야함
	@Autowired //생성자 위에 오토와이어드
	private final MemberRepository memberRepository;
	//private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //고정을 정률로 바꿔주자
	//private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	private final DiscountPolicy discountPolicy; //이렇게 하면 인터페이스에만 의존하는거니까 DIP 해결 ? ㅋㅋ 근데 구현은 없잔아 ... ;;

	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {//단일 책임 원칙 짱 잘 설계
		Member member = memberRepository.findById(memberId); //회원정보 조회하고
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice); //최종 생성된 주문을 반환
	}

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
