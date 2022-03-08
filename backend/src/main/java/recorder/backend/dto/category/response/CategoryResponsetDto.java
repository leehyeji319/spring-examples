package recorder.backend.dto.category.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import recorder.backend.domain.category.Category;
import recorder.backend.dto.category.CategoryPostsDto;

@Data
public class CategoryResponsetDto {

	private Long categoryId;
	private Long userId;
	private String categoryName;

	private List<CategoryPostsDto> posts;

	public CategoryResponsetDto(Category category) {
		categoryId = category.getId();
		//userId = category.getUser().getId();
		categoryName = category.getName();
		posts = category.getPosts().stream()
			.map(post -> new CategoryPostsDto(post))
			.collect(Collectors.toList());
	}
}
