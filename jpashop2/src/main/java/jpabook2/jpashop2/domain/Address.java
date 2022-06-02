package jpabook2.jpashop2.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable // 내장타입
@Getter //값타입은 변경이 되면 안됨, 생성할때만 set하고 변경이 안됨
public class Address {

	private String city;
	private String street;
	private String zipcode;

	protected Address() { //기본 생성자 생성
	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}
