package recorder.backend.dto.user.response;


import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserUpdateResponseDto {

	private Long userId;
	private String name;
	private String nickname;
	private String picture;
	private String domain;
	private String introduce;

}
