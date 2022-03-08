package recorder.backend.dto.category.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryUpdateResponseDto {

	private Long categoryId;
	private String categoryName;
}
