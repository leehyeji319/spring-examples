package sample.entity.domain;

import static jakarta.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	private String name;

	@ManyToMany //다대다관계도 연관관계 주입해야함
	@JoinTable(name = "category_feed",
		joinColumns = @JoinColumn(name = "category_id"),
		inverseJoinColumns = @JoinColumn(name = "feed_id")) //중간테이블 매핑을 해줘야함. 일대다 다대일로 풀어내는 중간테이블이 있어야함
	private List<Feed> feeds = new ArrayList<>();

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent") //셀프의 연관관계를 건거라고 생각하면 된다.
	private List<Category> child = new ArrayList<>();

	//==연관관계 편이 메서드==// parent니까 셀프
	public void addChildCategory(Category child) {
		this.child.add(child);
		child.setParent(this);
	}

}
