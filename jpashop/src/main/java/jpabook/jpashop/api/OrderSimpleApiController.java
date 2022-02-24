package jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * xToOne 인 것들 (ManyToOne, OneToOne) 성능 최적화
 * Order 조회
 * Order -> Member 연관 걸림
 * Order -> Delivery 연관 걸림
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

	private final OrderRepository orderRepository;

	@GetMapping("/api/v1/simple-orders")
	public List<Order> ordersV1() { //무한 루프에 감 member가니까 order있어.. order가니까 또 member있어ㅋㅋ
		List<Order> all = orderRepository.findAllByString(new OrderSearch()); //쌩짜로 주문 다들고옴
		for (Order order : all) {
			order.getMember().getName(); //여기까진 proxy. Lazy 강제 초기화
			order.getDelivery().getAddress();
		}
		return all;
	}


	@GetMapping("/api/v2/simple-orders")
	public List<SimpleOrderDto> ordersV2() { //리스트로 바로 반환하면 안되고 result로 감싸야하는거 아시죠?
		//ORDER 2개
		//N + 1 문제 -> 회원 N + 배송 N
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());

		//2개면 결과적으로 두번 루프가 돈다.
		List<SimpleOrderDto> result = orders.stream()
			.map(SimpleOrderDto::new) //생성자 한개니까 뭐 .. new로 가능
			.collect(Collectors.toList());

		return result;
	}

	@Data
	static class SimpleOrderDto {
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;

		public SimpleOrderDto(Order order) {
			orderId = order.getId();
			name = order.getMember().getName(); //LAZY 초기화 -> 영속성 컨텍스트가 memberId를 가지고 영속성 컨텍스트 찾아봐 없으면 이제 끌고오는거임
			orderDate = order.getOrderDate();
			orderStatus = order.getStatus();
			address = order.getDelivery().getAddress(); //LAZY 초기화
		}
	}
}
