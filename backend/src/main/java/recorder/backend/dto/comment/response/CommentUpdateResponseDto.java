package recorder.backend.dto.comment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentUpdateResponseDto {

	private Long commentId;
	private String content;
}
