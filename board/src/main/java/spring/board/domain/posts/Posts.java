package spring.board.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Posts {

	@Id @GeneratedValue
	private Long id;

	@Column(length = 500, nullable = false)
	private String title;	
}
