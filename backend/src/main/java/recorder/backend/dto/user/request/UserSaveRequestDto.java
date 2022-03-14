package recorder.backend.dto.user.request;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import recorder.backend.domain.user.User;


@Data
public class UserSaveRequestDto {

	private Long userId;
	@NotEmpty
	private String email;

	@NotEmpty
	private String password;
	@NotEmpty
	private String nickname;
	private String picture;
	@NotEmpty
	private String domain;
	@Size(max = 20)
	private String introduce;

	public User toEntity() {
		return User.builder()
			.email(this.email)
			.password(this.password)
			.nickname(this.nickname)
			.picture(this.picture)
			.domain(this.domain)
			.introduce(this.introduce)
			.build();
	}


}
