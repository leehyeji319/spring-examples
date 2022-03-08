package recorder.backend.dto.category.response;

import lombok.Data;
import recorder.backend.domain.category.Category;

@Data
public class CategoryNameDto {
	private Long categoryId;
	private String categoryName;

	public CategoryNameDto(Category entity) {
		this.categoryId = entity.getId();
		this.categoryName = entity.getName();
	}
}
