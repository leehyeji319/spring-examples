package recorder.backend.domain.tag;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import net.bytebuddy.agent.builder.AgentBuilder;

import lombok.Getter;
import lombok.Setter;
import recorder.backend.domain.post.PostTag;

@Getter @Setter
@Entity
public class Tag {

	@Id
	@GeneratedValue
	@Column(name = "tag_id")
	private Long id;

	@Column
	private String tagName;

	@OneToMany(mappedBy = "tag")
	private List<PostTag> postTags = new ArrayList<>();
}
