package recorder.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import recorder.backend.domain.category.Category;
import recorder.backend.domain.post.Post;
import recorder.backend.domain.user.User;
import recorder.backend.dto.post.request.PostSaveRequestDto;
import recorder.backend.dto.post.request.PostUpdateRequestDto;
import recorder.backend.dto.post.response.PostResponseDto;
import recorder.backend.dto.post.response.PostUpdateResponseDto;
import recorder.backend.repository.CategoryRepository;
import recorder.backend.repository.PostRepository;
import recorder.backend.repository.UserRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

	//생성
	@Transactional
	public Long savePost(PostSaveRequestDto requestDto) {
		User user = userRepository.findById(requestDto.getUser().getId()).get();
		Category category = categoryRepository.findById(requestDto.getCategory().getId()).get();
		requestDto.setUser(user);
		requestDto.setCategory(category);

		return postRepository.save(requestDto.toEntity()).getId();
	}

	//수정
	@Transactional
	public PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto updateDto) {
		Post findPost = postRepository.findById(postId).get();
		findPost.setTitle(updateDto.getTitle());
		findPost.setContent(updateDto.getTitle());
		findPost.setExposure(updateDto.getExposure());
		findPost.setThumnail_image(updateDto.getThumnailImage());
		findPost.setCategory(updateDto.getCategory());
		return new PostUpdateResponseDto(findPost.getId(),findPost.getCategory().getId(), findPost.getTitle(),
			findPost.getContent(), findPost.getSummary(), findPost.getThumnail_image());
	}

	//조회
	public List<PostResponseDto> findAllPost() {
		List<Post> allPost = postRepository.findAll();
		List<PostResponseDto> collect = allPost.stream()
			.map(PostResponseDto::new)
			.collect(Collectors.toList());

		return collect;
	}

	//삭제
	@Transactional
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}

}
