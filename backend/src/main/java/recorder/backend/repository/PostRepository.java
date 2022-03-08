package recorder.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import recorder.backend.domain.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
