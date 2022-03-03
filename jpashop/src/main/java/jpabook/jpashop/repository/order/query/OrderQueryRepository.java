package jpabook.jpashop.repository.order.query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

	private final EntityManager em;

	public List<OrderQueryDto> findOrderQueryDtos() { //얘도 결국 N + 1번 나가니까 최적화 해보자 V5
		List<OrderQueryDto> result = findOrders(); //컬렉션 부분은 안가져왔음 //얘 실행 쿼리 1번 -> 결과적으로 2개

		result.forEach(o -> { //여기서 컬렉션을 일일이 채워주죠? 밑에 쿼리를 일일이 날려주면서 하나씩 채워준다.
			List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId()); // Query N번
			o.setOrderItems(orderItems);
		}); //orderquerydto 에 컬렉션 값을 못채웠죠? 여기서 하나씩 넣어줘야해요
		return result;
	}

	private List<OrderItemQueryDto> findOrderItems(Long orderId) {
		return em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
					+
					" from OrderItem oi" +
					" join oi.item i" +
					" where oi.order.id = : orderId", OrderItemQueryDto.class)
			.setParameter("orderId", orderId)
			.getResultList();
	}

	private List<OrderQueryDto> findOrders() {
		return em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)"
					+
					" from Order o" +
					" join o.member m" +
					" join o.delivery d", OrderQueryDto.class)
			.getResultList();
	}

	public List<OrderQueryDto> findAllByDto_optimization() { //루트 조회(toOne 코드를 모두 한번에 조회)
		List<OrderQueryDto> result = findOrders();
		//orderItem 컬렉션을 MAP 한방에 조회
		Map<Long, List<OrderItemQueryDto>> orderItemMap =
			findOrderItemMap(toOrderIds(result));
		//루프를 돌면서 컬렉션 추가(추가 쿼리 실행X)
		result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

		return result;
	}

	private List<Long> toOrderIds(List<OrderQueryDto> result) {
		return result.stream()
			.map(o -> o.getOrderId())
			.collect(Collectors.toList());
	}

	private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
		List<OrderItemQueryDto> orderItems = em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) " +
					" from OrderItem oi" +
					" join oi.item i" +
					" where oi.order.id in :orderIds", OrderItemQueryDto.class)
			.setParameter("orderIds", orderIds)
			.getResultList();

		return orderItems.stream()
			.collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
	}

}


/*
	public List<OrderQueryDto> findAllByDto_optimization() {
		//이전꺼 단점이 roof 도는거니까 얘를 한번에 가져와보자
		List<OrderQueryDto> result = findOrders();

		List<Long> orderIds = result.stream()
			.map(o -> o.getOrderId()) //orderquery를 orderId로 바꿔 //orderIds에 주문 두개가 있겟지
			.collect(Collectors.toList());

		List<OrderItemQueryDto> orderItems = em.createQuery(
				"select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
					" from OrderItem oi" +
					" join oi.item i" +
					" where oi.order.id in : orderIds", OrderItemQueryDto.class)
			.setParameter("orderIds", orderIds)
			.getResultList();

		Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream() //map으로 바뀐 key가 order, 값은 걍 이거
			.collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));

		result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

		return result; //얘는 쿼리를 한번 날리고 메모리에서 매칭을 해가지고 값을 셋팅하는거임. 이렇게 하면 쿼리가 총 두번 나가겟죠?
	}*/
