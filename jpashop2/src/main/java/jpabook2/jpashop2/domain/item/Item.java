package jpabook2.jpashop2.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import jpabook2.jpashop2.domain.Category;
import jpabook2.jpashop2.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

@Entity
//상속관계매핑이기때문에 전략을 부모테이블에 잡아줘야함 우리는 싱글테이블이니까 여기에 잡아줘야해
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //singletable한테이블에 다 잡아주기
@DiscriminatorColumn(name = "dtype")
@Getter
//@Setter //Setter보다는 그냥 이 안에서 비즈니스 로직을 짜고 그 메서드를 이용하여 객체에 접근하는게 젤 좋음
public abstract class Item { //구현체로 선언

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;

	//상속관계 매핑을 해야함. 이렇게 세개는 공통 속성
	private String name;
	private int price;
	private int stockQuantity;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();

	//==비즈니스 로직==// 데이터를 가지고 있는 곳에서 비즈니스 로직이 나가는게 제일 응집도가 있겠죠?
	/**
	 * stock 증가
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	/*
	* stock 감소
	* */
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if (restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
}
