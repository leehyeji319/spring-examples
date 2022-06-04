package jpabook2.jpashop2.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String name;

	@Embedded //내장타입 둘중하나만잇어도 되는데 그냥 해줌(가독성을위해)
	private Address address;

	@OneToMany(mappedBy = "member") //나는 주인의 거울이에요. 하는 mappedBy를 해줘야함
	private List<Order> orders = new ArrayList<>(); //하이버네이트가 관리하는 컬렉션은 그냥 건들지 마세요
}
