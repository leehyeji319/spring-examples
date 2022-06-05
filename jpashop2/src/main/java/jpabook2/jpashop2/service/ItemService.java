package jpabook2.jpashop2.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook2.jpashop2.domain.Delivery;
import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.domain.Order;
import jpabook2.jpashop2.domain.OrderItem;
import jpabook2.jpashop2.domain.OrderStatus;
import jpabook2.jpashop2.domain.item.Book;
import jpabook2.jpashop2.domain.item.Item;
import jpabook2.jpashop2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}


	//준영속 엔티티 변경 감지 dirty checking 방법
	@Transactional
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId);
		findItem.setPrice(price);
		findItem.setName(name);
		findItem.setStockQuantity(stockQuantity);
		// findItem.setPrice(bookParam.getPrice());
		// findItem.setName(bookParam.getName());
		// findItem.setStockQuantity(bookParam.getStockQuantity());
		//여기서 더 할 필요없이 commit이 된다. -> Transactional에 의해서
		//Transaction이 commit이 되면 jpa는 flush를 날려서 영속성 컨텍스트에서 변경된 애를 다 찾어
		//어 변경이 됏네 ? 하고 쿼리를 날림
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

}
