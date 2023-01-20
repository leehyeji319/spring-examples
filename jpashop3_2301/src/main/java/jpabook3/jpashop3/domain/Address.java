package jpabook3.jpashop3.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable //어딘가의 내장이 될 수 있다. 내장타입
@Getter
public class Address { //내장타입. 얘는 Setter는 아예 만드는게 아님

	private String city;
	private String street;
	private String zipcode;

	protected Address() {
	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}
