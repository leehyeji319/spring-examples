package recorder.backend.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import recorder.backend.domain.user.User;
import recorder.backend.dto.user.LoginDto;
import recorder.backend.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final UserRepository userRepository;

	/**
	 * null이면 로그인 실패
	 */
	public User login(LoginDto loginDto) {
		return userRepository.findByEmail(loginDto.getLoginId())
			.filter(u -> u.getPassword().equals(loginDto.getPassword()))
			.orElse(null); //비번이 안맞음
	}
}
