package jpabook.jpashop.repository.order.simplequery;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryReposiotry {
	private final EntityManager em;

	public List<OrderSimpleQueryDto> findOrderDtos() {
		return em.createQuery(
				"select new jpabook.jpashop.repository.order.simplequery." +
					"OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " + //파라미터 넣어줘야해
					" from Order o" +
					" join o.member m" +
					" join o.delivery d", OrderSimpleQueryDto.class)
			.getResultList();
	}
}
