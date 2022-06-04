package jpabook2.jpashop2.domain;

import static javax.persistence.FetchType.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	//다대일 관계
	@ManyToOne(fetch = LAZY) //EAGER는 조인을 한꺼번에 해서 가져온다.
	@JoinColumn(name = "member_id") //foreignkey 매핑해주기 연관관계 주인도 정해줘야함
	private Member member;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //나는 거울이야..
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne(fetch = LAZY, cascade = CascadeType.ALL) //onetoone 이라도 연관관계 주인 잡아줘야함. 가까운거 잡아주면 좋지 ~
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	private LocalDateTime orderDate; //주문시간

	@Enumerated(EnumType.STRING)
	private OrderStatus status; //주문상태 [ORDER, CANCEL]

	//==연관관계 편의 메서드==// 양방향일때 연관관계 편의 메서드가 있으면 좋아요
	//원자적으로 한 코드로 딱 해준거임
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}

	//==생성 메서드==// ->앞으로 생성하는 시점만 바꾸면 이것만 바꾸면 되니까
	public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) { //...문법 -> 여러개 넘기기
		Order order = new Order();
		order.setMember(member);
		order.setDelivery(delivery);
		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		order.setStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		return order;
	}

	//==비즈니스 메서드==//

	/**
	 * 주문 취소
	 */
	public void cancel() {
		if (delivery.getStatus() == DelieryStatus.COMP) {
			throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.");
		}

		this.setStatus(OrderStatus.CANCEL);
		for (OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}

	//==조회 로직==//
	/**
	 * 전체 주문 가격 조회
	 */
	public int getTotalPrice () {
		int totalPrice = 0;
		for (OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getTotalPrice();
		}
		return totalPrice;
	}
}
