package sample.entity.domain;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Feed {

	@Id
	@GeneratedValue
	@Column(name = "feed_id")
	private Long feedId;

	@Column(name = "feed_title")
	private String feedTitle;

	@Column(name = "feed_content")
	private String feedContent;

	@Column(name = "feed_like_cnt")
	private int feedLikeCnt;

	@Column(name = "feed_comment_cnt")
	private int feedCommentCnt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Enumerated(EnumType.STRING)
	private FeedType feedType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "card_id")
	private Card card;

	@OneToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "bundle_id")
	private Bundle bundle;

	@OneToMany(mappedBy = "feed", cascade = ALL)
	private List<FeedLike> feedLikeList = new ArrayList<>();

	@OneToMany(mappedBy = "feed", cascade = ALL)
	private List<Comment> commentList = new ArrayList<>();

	@ManyToMany(mappedBy = "feeds")
	private List<Category> categoryList = new ArrayList<>();

}
