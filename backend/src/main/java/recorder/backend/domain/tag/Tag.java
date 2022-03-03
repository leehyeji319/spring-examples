package recorder.backend.domain.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Tag {

	@Id
	@GeneratedValue
	@Column(name = "tag_id")
	private Long id;

	@Column
	private String tagName;
}
