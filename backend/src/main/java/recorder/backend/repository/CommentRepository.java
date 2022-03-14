package recorder.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import recorder.backend.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
