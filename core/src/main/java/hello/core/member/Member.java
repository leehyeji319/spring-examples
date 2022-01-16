package hello.core.member;

public class Member {

	private Long id;
	private String name;
	private Grade grad;

	public Member(Long id, String name, Grade grad) {
		this.id = id;
		this.name = name;
		this.grad = grad;
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

	public Grade getGrad() {
		return grad;
	}

	public void setGrad(Grade grad) {
		this.grad = grad;
	}

}
