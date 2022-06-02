package recorder.backend.domain.user;

import static javax.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import recorder.backend.domain.BaseEntity;
import recorder.backend.domain.category.Category;
import recorder.backend.domain.comment.Comment;
import recorder.backend.domain.follow.Followers;
import recorder.backend.domain.follow.Followings;
import recorder.backend.domain.post.PostLike;

@Getter @Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(unique = true)
	private String email;

	//private String username;

	private String password;

	private String nickname;

	private String picture;

	private String domain;

	private String introduce;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


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

	public User() {

	}

	@Builder
	public User(String email, String password, String nickname, String picture, String domain,
		String introduce) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.picture = picture;
		this.domain = domain;
		this.introduce = introduce;
	}

	@Builder
	public User(String email, String password, String nickname, String domain,
		String introduce) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.domain = domain;
		this.introduce = introduce;
	}

}
