package recorder.backend.domain.category;

import static javax.persistence.CascadeType.*;
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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recorder.backend.domain.post.Post;
import recorder.backend.domain.user.User;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	@ManyToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "user_id")
	private User user;

	private String name;

	@OneToMany(mappedBy = "category", cascade = ALL)
	private List<Post> posts = new ArrayList<>();


	@Builder
	public Category(User user, String name) {
		this.user = user;
		this.name = name;
	}
}
