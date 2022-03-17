package recorder.backend.apicontroller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import recorder.backend.domain.user.User;
import recorder.backend.session.SessionConstants;

@RestController
public class HomeController {

	@GetMapping("/")
	public User home(HttpServletRequest request) {

		//세션이 없으면 홈으로 이동
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}

		//세션에 저장된 회원 조회
		User loginUser = (User) session.getAttribute(SessionConstants.LOGIN_USER);

		//세션에 회원 데이터가 없으면 홈으로 이동
		if (loginUser == null) {
			return null;
		}

		//세션이 유지되면 로그인으로 이동
		return loginUser;
	}
}
