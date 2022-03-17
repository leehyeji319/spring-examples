package recorder.backend.domain.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recorder.backend.domain.BaseEntity;
import recorder.backend.domain.category.Category;
import recorder.backend.domain.comment.Comment;
import recorder.backend.domain.user.User;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Post extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "post_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String title;

	private String content;

	private int hits = 0;

	private String summary;

	@Enumerated(EnumType.STRING)
	private Exposure exposure;

	private String thumbnail_image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<PostLike> postLikes = new ArrayList<>();

	@OneToMany(mappedBy = "post")
	private List<PostTag> postTags = new ArrayList<>();

	@Builder
	public Post(User user, String title, String content, int hits, String summary,
		Exposure exposure, String thumbnail_image, Category category) {
		this.user = user;
		this.title = title;
		this.content = content;
		this.hits = hits;
		this.summary = summary;
		this.exposure = exposure;
		this.thumbnail_image = thumbnail_image;
		this.category = category;
	}

	//조회수
	public void addHits() {
		this.hits += 1;
	}
}
