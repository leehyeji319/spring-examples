package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String name;

	@Embedded //내장타입이란 뜻
	private Address address;

	@JsonIgnore //이거 넣으면 json에는 나오지 않는다. 양방향 연관관계가 있으면 json ignore해줘야함
	@OneToMany(mappedBy = "member") //1대 다 관계 order table에 있는 member에 의해서 매핑된 거울일 뿐이야.. 읽기전용이란 뜻
	private List<Order> orders = new ArrayList<>();
}
