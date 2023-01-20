package jpabook3.jpashop3.domain;

import static jakarta.persistence.FetchType.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;

	//관계설정
	@ManyToOne(fetch = LAZY)
	//mapping을 뭘로 할꺼냐. 양방향이면 연관관계의 주인을 정해줘야함
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne(fetch = LAZY) //1대1은 포링키를 어디에 두냐에 장단점이있음. 액세스 많이 하는곳에 넣는게 좋음
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	//hibernate가 자동으로 지원해줌
	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status; //주문 상태 order, cancle
}
