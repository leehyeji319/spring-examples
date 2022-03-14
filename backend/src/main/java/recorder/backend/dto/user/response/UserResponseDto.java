package recorder.backend.dto.user.response;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import recorder.backend.domain.user.Role;
import recorder.backend.domain.user.User;

@Data
public class UserResponseDto {


	private Long userId;
	private String email;
	private String nickname;
	private String picture;
	private String domain;
	private String introduce;
	private Role role;

	public UserResponseDto(User user) {
		this.userId = user.getId();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.picture = user.getPicture();
		this.domain = user.getDomain();
		this.introduce = user.getIntroduce();
		this.role = user.getRole();
	}
}
