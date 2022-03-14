package recorder.backend.dto.comment.response;

import lombok.Data;
import recorder.backend.domain.comment.Comment;

@Data
public class CommentResponseDto {

	private Long commentId;
	private Long userId;
	private Long postId;
	private String content;

	public CommentResponseDto(Comment comment) {
		this.commentId = comment.getId();
		this.userId = comment.getUser().getId();
		this.postId = comment.getPost().getId();
		this.content = comment.getContent();
	}
}
