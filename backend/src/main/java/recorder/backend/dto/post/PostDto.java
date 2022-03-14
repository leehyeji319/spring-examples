package recorder.backend.dto.post;

import lombok.Data;
import recorder.backend.domain.post.Exposure;
import recorder.backend.domain.post.Post;

//조회 Dto

@Data
public class PostDto {

	private Long postId;
	private String title;
	private String content;
	private String summary;
	private int hits;
	private Exposure exposure;
	private String thumnailImage;

	public PostDto(Post post) {
		this.postId = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.summary = post.getSummary();
		this.hits = post.getHits();
		this.exposure = post.getExposure();
		this.thumnailImage = post.getThumnail_image();
	}
}
