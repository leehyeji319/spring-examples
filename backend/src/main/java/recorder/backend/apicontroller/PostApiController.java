package recorder.backend.apicontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import recorder.backend.domain.post.Post;
import recorder.backend.dto.post.request.PostSaveRequestDto;
import recorder.backend.dto.post.request.PostUpdateRequestDto;
import recorder.backend.dto.post.response.PostResponseDto;
import recorder.backend.dto.post.response.PostUpdateResponseDto;
import recorder.backend.repository.PostRepository;
import recorder.backend.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostApiController {

	private final PostRepository postRepository;
	private final PostService postService;

	//생성
	@PostMapping("/board/posts")
	public Long savePost(@RequestBody PostSaveRequestDto requestDto) {
		return postService.savePost(requestDto);
	}

	//수정
	@PutMapping("/board/posts/{post_id}")
	public PostUpdateResponseDto updatePost(@PathVariable("post_id") Long postId, @RequestBody PostUpdateRequestDto updateDto) {
		return postService.updatePost(postId, updateDto);
	}

	//조회
	@GetMapping("/board/posts")
	public Result allPosts() {
		List<PostResponseDto> allPost = postService.findAllPost();

		return new Result(allPost);
	}

	//하나만 조회
	@GetMapping("/board/posts/{post_id}")
	public PostResponseDto findPost(@PathVariable("post_id") Long postId) {
		Post findPost = postRepository.findById(postId).get();
		return new PostResponseDto(findPost);
	}

	//삭제
	@DeleteMapping("/board/posts/{post_id}")
	public void deletePost(@PathVariable("post_id") Long postId) {
		postService.deletePost(postId);

	}

	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
}
