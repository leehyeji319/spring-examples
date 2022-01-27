package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable //jpa 내장 타입이기 때문에
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;
}
