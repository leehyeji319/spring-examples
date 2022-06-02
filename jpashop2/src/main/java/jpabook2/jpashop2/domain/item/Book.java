package jpabook2.jpashop2.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B") //구분이되는걸 잡아줘야함
@Getter @Setter
public class Book extends Item {

	private String author;
	private String isbn;
}
