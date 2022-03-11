package recorder.backend.dto.post.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import recorder.backend.domain.category.Category;
import recorder.backend.domain.post.Exposure;
import recorder.backend.domain.post.Post;
import recorder.backend.domain.user.User;

@Data
public class PostSaveRequestDto {


	private Long postId;
	private Category category;
	private User user;

	private Long userId;
	private Long categoryId; //여기 구현 제대로 하기

	@NotEmpty
	@Size(min = 1, max = 20)
	private String title;
	@NotEmpty
	@Size(min = 1)
	private String content;
	private String summary;
	private String exposure;
	private String thumnail_image;

	public Post toEntity() {
		return Post.builder()
			.user(this.user)
			.category(this.category)
			.title(this.title)
			.content(this.content)
			.summary(this.summary)
			.exposure(Exposure.valueOf(this.exposure))
			.thumnail_image(this.thumnail_image)
			.build();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
