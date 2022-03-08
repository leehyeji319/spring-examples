package recorder.backend.dto.category.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recorder.backend.exception.IllegalUserException;
import recorder.backend.repository.CategoryRepository;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequestDto {
	private CategoryRepository categoryRepository;

	private String categoryName;

}
