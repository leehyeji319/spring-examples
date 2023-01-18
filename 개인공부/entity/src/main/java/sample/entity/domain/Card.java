package sample.entity.domain;

import static jakarta.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private Long cardId;

	private int scrapCnt;

	private String description;

	private String commentary;

	@Enumerated(EnumType.STRING)
	private CardType cardType;

	@OneToMany(mappedBy = "card", cascade = ALL)
	private List<CardBundle> cardBundleList = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = ALL)
	@JoinColumn(name = "link_id")
	private Link link;

	@OneToOne(mappedBy = "card", fetch = FetchType.LAZY)
	private Feed feed;
}
