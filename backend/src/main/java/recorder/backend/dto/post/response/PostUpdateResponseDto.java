package recorder.backend.dto.post.response;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostUpdateResponseDto {

	private Long postId;
	private Long categoryId;
	private String title;
	private String content;
	private String exposure;
	private String thumnailImage;
}
