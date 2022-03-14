package recorder.backend.domain.user;

import static javax.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import recorder.backend.domain.BaseEntity;
import recorder.backend.domain.category.Category;
import recorder.backend.domain.comment.Comment;
import recorder.backend.domain.follow.Followers;
import recorder.backend.domain.follow.Followings;
import recorder.backend.domain.post.PostLike;

@Getter @Setter
@Entity
@NoArgsConstructor
public class User extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(unique = true)
	private String email;

	private String password;

	private String nickname;

	private String picture;

	private String domain;

	private String introduce;

	@Enumerated(EnumType.STRING)
	@Column
	private Role role;


	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<Category> categoryList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<Comment> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<PostLike> postLikeList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<Followings> followingsList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = ALL)
	private List<Followers> followersList = new ArrayList<>();

/*
	@Builder
	public User(String email, String name, String picture, Role role) {
		this.email = email;
		this.name = name;
		this.picture = picture;
		this.role = role;
	}
*/

	@Builder
	public User(String email, String password, String nickname, String picture, String domain,
		String introduce, Role role) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.picture = picture;
		this.domain = domain;
		this.introduce = introduce;
		this.role = role;
	}

/*	public User update(String name, String picture, String nickname, String domain, String introduce, Role role) {
		this.name = name;
		this.picture = picture;
		this.nickname = nickname;
		this.domain = domain;
		this.introduce = introduce;
		this.role = role;

		return this;
	}*/

	public String getRoleKey() {
		return this.role.getKey();
	}
}
