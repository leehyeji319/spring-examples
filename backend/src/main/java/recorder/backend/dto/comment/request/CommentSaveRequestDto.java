package recorder.backend.dto.comment.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import recorder.backend.domain.comment.Comment;
import recorder.backend.domain.post.Post;
import recorder.backend.domain.user.User;

@Data
public class CommentSaveRequestDto {

	private Long userId;
	private Long postId;
	private Long commentId;

	private User user;
	private Post post;

	@NotEmpty
	@Size(min = 1, max = 150)
	private String content;

	public Comment toEntity() {
		return Comment.builder()
			.content(this.content)
			.user(this.user)
			.post(this.post)
			.build();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
