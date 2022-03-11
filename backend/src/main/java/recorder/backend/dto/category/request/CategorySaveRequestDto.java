package recorder.backend.dto.category.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;
import recorder.backend.domain.category.Category;
import recorder.backend.domain.user.User;

@NoArgsConstructor
@Data
public class CategorySaveRequestDto {

	private Long cateogryId;

	private User user;
	private Long userId;


	@NotEmpty
	@Length(max = 10)
	private String categoryName;

	public Category toEntity() {
		return Category.builder()
			.user(this.user)
			.name(this.categoryName)
			.build();
	}

	public void setUser(User user) {
		this.user = user;
	}

}
