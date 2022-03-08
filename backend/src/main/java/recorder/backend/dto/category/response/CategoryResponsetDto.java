package recorder.backend.dto.category.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import recorder.backend.domain.category.Category;
import recorder.backend.dto.category.CategoryPostDto;

@Data
public class CategoryResponsetDto {

	private Long categoryId;
	private Long userId;
	private String categoryName;

	private List<CategoryPostDto> categoryPostDtos;

	public CategoryResponsetDto(Category category) {
		categoryId = category.getId();
		//userId = category.getUser().getId();
		categoryName = category.getName();
		categoryPostDtos = category.getPostList().stream()
			.map(categoryPost -> new CategoryPostDto(categoryPost))
			.collect(Collectors.toList());
	}

}
