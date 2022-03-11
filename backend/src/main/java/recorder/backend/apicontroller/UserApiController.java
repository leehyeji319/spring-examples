package recorder.backend.apicontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import recorder.backend.dto.user.request.UserSaveRequestDto;
import recorder.backend.dto.user.request.UserUpdateRequestDto;
import recorder.backend.dto.user.response.UserResponseDto;
import recorder.backend.dto.user.response.UserUpdateResponseDto;
import recorder.backend.repository.UserRepository;
import recorder.backend.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserRepository userRepository;
	private final UserService userService;

	//생성
	@PostMapping("/users/account/signup")
	public Long saveUser(@RequestBody UserSaveRequestDto requestDto) {
		return userService.saveUser(requestDto);
	}

	//수정
	@PutMapping("/users/{user_id}")
	public UserUpdateResponseDto updateUser(@PathVariable("user_id") Long userId, @RequestBody UserUpdateRequestDto updateDto) {
		return userService.updateUser(userId, updateDto);
	}

	//조회
	@GetMapping("/users")
	public Result allUsers() {
		List<UserResponseDto> allUser = userService.findAllUser();

		return new Result(allUser);
	}

	//삭제
	@DeleteMapping("/users/{user_id}")
	public void deleteUser(@PathVariable("user_id") Long userId) {
		userService.deleteUser(userId);
	}


	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
}
