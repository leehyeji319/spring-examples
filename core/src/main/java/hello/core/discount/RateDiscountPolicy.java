package hello.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

@Component
@MainDiscountPolicy //내가 만든 어노테이션
public class RateDiscountPolicy implements DiscountPolicy{

	private final int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrad() == Grade.VIP) {
			return price * discountPercent / 100; // 이거되게 어려워요 10퍼센트 하는게..
		} else {
			return 0;
		}
	}
}
