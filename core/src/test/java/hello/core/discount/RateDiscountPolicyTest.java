package hello.core.discount;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;

class RateDiscountPolicyTest {

	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

	@Test
	@DisplayName("vip는 10% 할인이 적용되어야 한다")
	void vip_o() {
		//given
		Member member = new Member(1L, "memberVIP", Grade.VIP);
		//when
		int discount = discountPolicy.discount(member, 10000);
		//then
		assertThat(discount).isEqualTo(1000); //Assertions는 스태틱 임포트로 하는게 좋아요 간결ㅎㅎ
	}

	//실패 테스트도 만들어봐야함
	@Test
	@DisplayName("vip가 아니면 할인이 적용되지 않아야 한다")
	void vip_x() {
		//given
		Member member = new Member(2L, "memberBASIC", Grade.BASIC);
		//when
		int discount = discountPolicy.discount(member, 10000);
		//then
		assertThat(discount).isEqualTo(0);
	}
}