package sample.entity.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identity로 하면 디비엔진에 따라 오토 인크리먼트가 안먹는다.
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_email", unique = true)
	private String userEmail;

	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_nickname", unique = true)
	private String userNickname;

	@Column(name = "user_profile_image")
	private String userProfileImage;
	@Column(name = "user_introduction")
	private String userIntroduction;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<FeedLike> feedLikeList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Feed> feedList = new ArrayList<>();
}
