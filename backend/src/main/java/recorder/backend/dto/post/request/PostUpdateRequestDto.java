package recorder.backend.dto.post.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recorder.backend.domain.category.Category;
import recorder.backend.domain.post.Exposure;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostUpdateRequestDto {

	@NotEmpty
	@Size(min = 1, max = 20)
	private String title;
	@NotEmpty
	@Size(min = 1)
	private String content;
	private Exposure exposure;
	private String thumnailImage;
	private Category category;
}
