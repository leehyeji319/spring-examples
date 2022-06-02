package recorder.backend.dto.loginlogout.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtAuthenticationResponse {

	private String accessToken;
	private String tokenType = "Bearer";

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
