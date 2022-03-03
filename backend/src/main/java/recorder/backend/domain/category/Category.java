package recorder.backend.domain.category;

import static javax.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import recorder.backend.domain.post.Post;
import recorder.backend.domain.user.User;

@Getter @Setter
@Entity
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	@ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	private String name;

	@OneToMany(mappedBy = "category")
	private List<Post> postList = new ArrayList<>();

}
