package jpabook.jpashop.repository.order.simplequery;

import java.time.LocalDateTime;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSimpleQueryDto {
	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;

	public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate,
		OrderStatus orderStatus, Address address) { //엔티티를 직접 받을 수 없음
		this.orderId = orderId;
		this.name = name; //LAZY 초기화 -> 영속성 컨텍스트가 memberId를 가지고 영속성 컨텍스트 찾아봐 없으면 이제 끌고오는거임
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.address = address; //LAZY 초기화
	}
}