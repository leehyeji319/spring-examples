package recorder.backend.dto.category;

import lombok.Data;
import recorder.backend.domain.post.Post;

/**
 * 카테고리 조회시 딸려있는 포스트 가져오기
 */
@Data
public class CategoryPostsDto {

	private String title;
	private String content;
	private int hits;
	private String summary;
	private String exposure;
	private String thumnailImage;

	public CategoryPostsDto(Post post) {
		title = post.getTitle();
		content = post.getContent();
		hits = post.getHits();
		summary = post.getSummary();
		exposure = String.valueOf(post.getExposure());
		thumnailImage = post.getThumnail_image();
	}
}
