package recorder.backend.dto.loginlogout.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpRequest {

	@NotBlank
	@Size(min = 4, max = 40)
	private String email;

	@NotBlank
	@Size(min = 2, max = 15)
	private String nickname;

	@NotBlank
	@Size(min = 3, max = 15)
	private String domain;

	@Size(max = 50)
	private String introduce;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;
}
