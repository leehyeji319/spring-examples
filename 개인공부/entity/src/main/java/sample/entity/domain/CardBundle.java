package sample.entity.domain;

import static jakarta.persistence.FetchType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CardBundle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_bundle_id")
	private Long cardBundleId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "bundle_id")
	private Bundle bundle;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "card_id")
	private Card card;
}
