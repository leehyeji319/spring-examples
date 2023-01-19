package jpabook3.jpashop3.domain.item;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jpabook3.jpashop3.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한테이블에 다 때려박는거임
@DiscriminatorColumn(name = "dtype") //book이면 어케할거야
public abstract class Item { //상속관계 매핑을 해야함. 전략을 해줘야함 우리는 싱글테이블

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;

	//아래는 공통속성이지
	private String name;
	private int price;
	private int stockQuantity;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
}
