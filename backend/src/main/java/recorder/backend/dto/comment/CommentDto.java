package recorder.backend.dto.comment;

import lombok.Data;
import recorder.backend.domain.comment.Comment;


//조회 Dto
@Data
public class CommentDto {

	private Long commentId;
	private String content;

	public CommentDto(Comment comment) {
		commentId = comment.getId();
		content = comment.getContent();
	}
}
