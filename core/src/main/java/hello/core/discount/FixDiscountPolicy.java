package hello.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;

@Component //rate fix 둘다 빈으로 등록해보기
public class FixDiscountPolicy implements DiscountPolicy{

	private int discountFixAmount = 1000; //1000원 할인
	@Override
	public int discount(Member member, int price) {
		if (member.getGrad() == Grade.VIP) {
			return discountFixAmount;
		} else {
			return 0;
		}
	}
}
