package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;

	public void save(Item item) { //id 값이 없다 = 완전히 새로 생성한 객체라는것
		if (item.getId() == null) {
			em.persist(item); //getid 가 널이면 persist에 아이템을 저장
		} else { //null이 아니면 merge로 가야쥐~~
			Item merge = em.merge(item);
			//merge - 영속성 컨텍스트에서 관리되는애 , item - 파라미터로 넘어온것뿐 영속성 컨텍스트에서 관리x
		}
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() { //여러개 조회는 jpql을 써야겟지
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}

}
