package recorder.backend.config.auth.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import recorder.backend.domain.user.Role;
import recorder.backend.domain.user.User;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
		String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	//of() -> OAuth2User에서 반환하는 사용자 정보는 Map이기 땜에 값 하나하나를 변환해야한다.
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
		Map<String, Object> attributes) {
		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
			.name((String) attributes.get("name"))
			.email((String) attributes.get("email"))
			.picture((String) attributes.get("picture"))
			.attributes(attributes)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}

	//OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할때. 가입할때 기본권한은 GEUST
	public User toEntity() {
		return User.builder()
			.email(email)
			.name(name)
			.picture(picture)
			.role(Role.USER)
			.build();
	}

}
