package recorder.backend.dto.user.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recorder.backend.domain.user.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequestDto {

	@NotEmpty
	private String name;
	@NotEmpty
	private String nickname;
	private String picture;

	@NotEmpty
	private String domain;

	@Size(max = 20)
	private String introduce;
}
