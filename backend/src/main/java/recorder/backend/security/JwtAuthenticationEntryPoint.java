package recorder.backend.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 우리가 정의할 첫 번째 스프링 보안 관련 클래스는 JwtAuthenticationEntryPoint.
 * AuthenticationEntryPoint인터페이스를 구현하고 해당 메서드에 대한 구현을 제공 합니다 commence().
 * 이 메서드는 인증되지 않은 사용자가 인증이 필요한 리소스에 액세스하려고 하여 예외가 throw될 때마다 호출됩니다.
 * 이 경우 예외 메시지가 포함된 401 오류로 간단히 응답합니다.
 */


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	@Override
	public void commence(HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		AuthenticationException e) throws IOException, ServletException {
		logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
	}
}
