package sample.entity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Link {

	@Id
	@GeneratedValue
	@Column(name = "link_id")
	private Long linkId;

	@Column(name = "link_url")
	private String linkUrl;

	@Column(name = "link_image")
	private String linkImage;
	@Column(name = "link_title")
	private String linkTitle;

	@Column(name = "link_description")
	private String linkDescription;

	@OneToOne(mappedBy = "link", fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "card_id")
	private Card card;
}
