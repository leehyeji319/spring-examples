package recorder.backend.apicontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import recorder.backend.domain.category.Category;
import recorder.backend.dto.category.request.CategoryUpdateRequestDto;
import recorder.backend.dto.category.request.CategorySaveRequestDto;
import recorder.backend.dto.category.response.CategoryNameDto;
import recorder.backend.dto.category.response.CategoryResponsetDto;
import recorder.backend.dto.category.response.CategoryUpdateResponseDto;
import recorder.backend.repository.CategoryRepository;
import recorder.backend.service.CategoryService;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

	private final CategoryRepository categoryRepository;
	private final CategoryService categoryService;

	//카테고리 생성
	@PostMapping("/board/categories")
	@ResponseBody
	public Long saveCategory(@RequestBody CategorySaveRequestDto requestDto) {
		return categoryService.saveCategory(requestDto);
	}

	//조회
	@GetMapping("/board/categories")
	public Result allCategories() {
		List<CategoryNameDto> categoryNameDtos = categoryService.showCategoryNames();

		return new Result(categoryNameDtos);
	}

	//한개만 조회
	@GetMapping("/board/categories/{category_id}")
	public CategoryResponsetDto findCategory(@PathVariable("category_id") Long categoryId) {
		Category findCategory = categoryRepository.findById(categoryId).get();
		return new CategoryResponsetDto(findCategory);
	}

	//수정
	@PutMapping("/board/categories/{category_id}")
	public CategoryUpdateResponseDto updateCategory(@PathVariable("category_id") Long categoryId, CategoryUpdateRequestDto updateDto) {
		Long updateCategory = categoryService.updateCategory(categoryId, updateDto);
		Category updateSaveCategory = categoryRepository.findById(updateCategory).get();
		return new CategoryUpdateResponseDto(updateSaveCategory.getId(), updateSaveCategory.getName());
	}

	//삭제
	@DeleteMapping("/board/categories/{category_id}")
	public void deleteCateogry(@PathVariable("category_id") Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}



	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
}
