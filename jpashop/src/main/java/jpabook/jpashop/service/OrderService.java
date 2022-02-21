package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;

	/**
	 * 주문
	 */
	@Transactional //값 변경해야하니까 transactional
	public Long order(Long memberId, Long itemId, int count) {

		//엔티티 조회
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);

		//배송정보 설정
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());

		//주문 상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);

		//주문 저장
		orderRepository.save(order); //cacade를 해서 order만 저장해도 delivery랑 orderitem이 persist가 된것.
		//라이프 사이클이 맞고,order말곤 delivery랑 orderitem을 누가 안쓰니까 cascade를 쓴거야..

		return order.getId(); //order 식별자 값만 넘겨줄게요
	}

	/**
	 * 주문 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) { //주문 취소할때 오더 아이디만 내려옴
		//주문 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		//주문 취소
		order.cancel();
	}

	/**
	 * 주문 검색
	 */
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAllByString(orderSearch);
	}
}
