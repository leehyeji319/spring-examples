package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable //jpa 내장 타입이기 때문에
@Getter
public class Address {

	protected Address() { //생성자는 protected로 선언 jpa 스펙상 만들어준것이다.
	}

	private String city;
	private String street;
	private String zipcode;
}
