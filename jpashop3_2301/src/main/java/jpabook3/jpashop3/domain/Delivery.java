package jpabook3.jpashop3.domain;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

	@Id
	@GeneratedValue
	@Column(name = "delivery_id")
	private Long id;

	@OneToOne(fetch = LAZY)
	private Order order;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING) //ordinal은 숫자로 들어가 만약 ready, comp에서 다른 xx추가 하면 개망하는거임
	private DeliveryStatus status; //ready, comp
}
