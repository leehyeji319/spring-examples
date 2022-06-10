package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "USER")
public class Member {

	@Id
	private Long id;

	//@Column(name = "username")
	private String name;

	public Member() { //jpa는 기본적으로 내부적으로 reflection같은걸 써서, 동적으로 객체를 생성해내야한다. 기본생성자잇어야함
	}

	public Member(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
