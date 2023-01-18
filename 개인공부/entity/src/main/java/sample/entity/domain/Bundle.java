package sample.entity.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bundle {

	@Id
	@GeneratedValue
	@Column(name = "bundle_id")
	private Long bundleId;

	@Column(name = "bundle_thumbnail")
	private String bundleThumbnail;

	@Column(name = "bundle_thumbnail_text")
	private String bundleThumbnailText;

	@Column(name = "bundle_is_public")
	private boolean isBundlePublic;

	@OneToMany(mappedBy = "bundle", cascade = CascadeType.ALL)
	private List<CardBundle> cardBundleList = new ArrayList<>();

	@OneToOne(mappedBy = "bundle", fetch = FetchType.LAZY)
	private Feed feed;
}
