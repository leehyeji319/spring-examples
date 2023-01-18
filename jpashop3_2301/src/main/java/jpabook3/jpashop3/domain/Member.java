package jpabook3.jpashop3.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String name;

	@Embedded //암배더블이나 이거나 둘중 하나만 잇음 됨
	private Address address;

	@OneToMany(mappedBy = "member") //order table에 잇는 멤버 필드에 의해서 나는 매핑된거야.. 읽기전용이 되는거야
	private List<Order> orders = new ArrayList<>();

}
