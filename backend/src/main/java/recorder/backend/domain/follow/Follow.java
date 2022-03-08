/*
package recorder.backend.domain.follow;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import recorder.backend.domain.user.User;

public class Follow {

	@Id @GeneratedValue
	@Column(name = "follow_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
*/
