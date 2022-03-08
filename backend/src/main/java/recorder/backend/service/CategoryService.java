package recorder.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import recorder.backend.domain.category.Category;
import recorder.backend.domain.user.User;
import recorder.backend.dto.category.request.CategoryUpdateRequestDto;
import recorder.backend.dto.category.request.CategorySaveRequestDto;
import recorder.backend.dto.category.response.CategoryNameDto;
import recorder.backend.dto.category.response.CategoryUpdateResponseDto;
import recorder.backend.exception.IllegalUserException;
import recorder.backend.repository.CategoryRepository;
import recorder.backend.repository.UserRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

	//카테고리 생성
	@Transactional
	public Long saveCategory(CategorySaveRequestDto requestDto) {
		/*User user = userRepository.findById(requestDto.getUser().getId()).get();
		requestDto.setUser(user);*/
		validateDuplicatedCategoryName(requestDto.getCategoryName());
		return categoryRepository.save(requestDto.toEntity()).getId();
	}

	//카테고리 수정
	@Transactional
	public CategoryUpdateResponseDto updateCategory(Long categoryId, CategoryUpdateRequestDto updateDto) {
		Category findCategory = categoryRepository.findById(categoryId).get();
		validateDuplicatedCategoryName(updateDto.getCategoryName());
		findCategory.setName(updateDto.getCategoryName());
		return new CategoryUpdateResponseDto(findCategory.getId(), findCategory.getName());
	}

	//카테고리 삭제
	@Transactional
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	//글 작성 폼에 카테고리 뿌려주기, 카테고리 조회시 씀
	public List<CategoryNameDto> showCategoryNames() {
		return categoryRepository.findAll()
			.stream()
			.map(CategoryNameDto::new)
			.collect(Collectors.toList());
	}

	private void validateDuplicatedCategoryName(String categoryName) {
		if (categoryRepository.findByName(categoryName).isPresent()) {
			throw new IllegalUserException("이미 존재하는 카테고리입니다.");
		}
	}
}
