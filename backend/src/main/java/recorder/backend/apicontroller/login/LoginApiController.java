package recorder.backend.apicontroller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import recorder.backend.domain.user.User;
import recorder.backend.dto.user.LoginDto;
import recorder.backend.exception.IllegalUserException;
import recorder.backend.service.LoginService;
import recorder.backend.session.SessionConstants;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

	private final LoginService loginService;

	@PostMapping("/users/account/login")
	public User login(LoginDto loginDto, HttpServletRequest request) {

		User loginUser = loginService.login(loginDto);

		if (loginUser == null) {
			throw new IllegalUserException("아이디 또는 비밀번호가 맞지 않습니다.");
		}

		//로그인 성공 처리
		// 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
		HttpSession session = request.getSession();
		// 세션에 로그인 회원 정보 보관
		session.setAttribute(SessionConstants.LOGIN_USER, loginUser);
		return loginUser; // <- 필요한 정보만 프론트로 넘기게끔 수정예정.. dto로
	}

/*		HttpServletRequest#getSession에는 boolean 타입의 인자를 넘길 수 있는데,
		true를 넘길 경우 세션이 없으면 신규 세션을 생성하여 반환한다.
			false를 넘기면 세션이 없으면 그냥 null을 반환한다. 기본값은 true이다.
*/
	@PostMapping("/usrs/account/logout")
	public void logout(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); //세션 날림
		}
		//페이지 넘겨주기

	}
}
