package recorder.backend.dto.post.response;

import lombok.Data;
import recorder.backend.domain.post.Exposure;
import recorder.backend.domain.post.Post;

@Data
public class PostResponseDto {
	private Long postId;
	private Long userId;
	private Long categoryId;
	private String title;
	private String content;
	private String summary;
	private int hits;
	private Exposure exposure;
	private String thumnailImage;

	public PostResponseDto(Post post) {
		this.postId = post.getId();
//		this.userId = post.getUser().getId();
//		this.categoryId = post.getCategory().getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.summary = post.getSummary();
		this.hits = post.getHits();
		this.exposure = post.getExposure();
		this.thumnailImage = post.getThumnail_image();
	}
}
