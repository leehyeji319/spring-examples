package jpabook3.jpashop3.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
//싱글테이블이니까 저장을 할 때 디비 입장에서 구별할 수 있어야하니까
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item {

	private String author;
	private String isbn;
}
