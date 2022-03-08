package recorder.backend.config.auth.dto;

import java.io.Serializable;

import lombok.Getter;
import recorder.backend.domain.user.User;

@Getter
public class SessionUser implements Serializable {

	private String name;
	private String email;
	private String picture;

	public SessionUser(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
