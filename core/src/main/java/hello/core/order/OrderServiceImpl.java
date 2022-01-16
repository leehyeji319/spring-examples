package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

	private final MemberRepository memberRepository = new MemoryMemberRepository(); //new 해서 구현체를 생성해서 사용하고있다.
	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {//단일 책임 원칙 짱 잘 설계
		Member member = memberRepository.findById(memberId); //회원정보 조회하고
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice); //최종 생성된 주문을 반환
	}
}
