package recorder.backend.domain.post;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import recorder.backend.domain.user.User;

@Getter @Setter
@Entity
public class PostLike {

	@Id
	@GeneratedValue
	@Column(name = "post_like_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
