package sample.entity.domain;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FeedCategory {

	@Id
	@GeneratedValue
	@Column(name = "feed_category_id")
	private Long feedCategoryId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "feed_id")
	private Feed feed;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
}
