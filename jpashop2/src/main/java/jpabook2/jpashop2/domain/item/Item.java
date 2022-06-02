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
import lombok.Getter;
import lombok.Setter;

@Entity
//상속관계매핑이기때문에 전략을 부모테이블에 잡아줘야함 우리는 싱글테이블이니까 여기에 잡아줘야해
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //singletable한테이블에 다 잡아주기
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item { //구현체로 선언

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;

	//상속관계 매핑을 해야함. 이렇게 세개는 공통 속성
	private String name;
	private int price;
	private int stockQuantity;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
}
