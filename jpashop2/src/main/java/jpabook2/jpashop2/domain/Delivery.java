package jpabook2.jpashop2.domain;

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
@Getter
@Setter
public class Delivery {

	@Id
	@GeneratedValue
	@Column(name = "delivery_id")
	private Long id;

	@OneToOne(mappedBy = "delivery", fetch = LAZY) //거울
	private Order order;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING) //enumtype을 넣을때 이거 넣어야함 EnumType.ORDINAL 이거는 안됨 숫자로 들어감
	private DelieryStatus status; //READY, COMP
}
