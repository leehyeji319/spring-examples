package jpabook.jpashop.domain;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

	@Id @GeneratedValue
	@Column(name = "delivery_id")
	private Long id;

	@OneToOne(mappedBy = "delivery", fetch = LAZY)
	private Order order;

	@Embedded //내장타입
	private Address address;

	//enum타입은 꼭 애노테이션 넣아야돼. 디폴트가 오디널(컬럼에 숫자로 들어감)
	@Enumerated(EnumType.STRING) //ORDINAL절대 쓰면안됨@!!
	private DeliveryStatus status; //READY, COMP
}
