package recorder.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import recorder.backend.domain.user.User;
import recorder.backend.dto.user.request.UserSaveRequestDto;
import recorder.backend.dto.user.request.UserUpdateRequestDto;
import recorder.backend.dto.user.response.UserResponseDto;
import recorder.backend.dto.user.response.UserUpdateResponseDto;
import recorder.backend.exception.IllegalUserException;
import recorder.backend.repository.UserRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	//생성
	@Transactional
	public Long saveUser(UserSaveRequestDto requestDto) {
		validateDuplicateEmail(requestDto.getEmail());
		validateDuplicateDomain(requestDto.getDomain());
		return userRepository.save(requestDto.toEntity()).getId();
	}

	//수정
	@Transactional
	public UserUpdateResponseDto updateUser(Long userId, UserUpdateRequestDto updateDto) {
		validateDuplicateDomain(updateDto.getDomain());

		User findUser = userRepository.findById(userId).get();
		findUser.setName(updateDto.getName());
		findUser.setNickname(updateDto.getNickname());
		findUser.setPicture(updateDto.getPicture());
		findUser.setDomain(updateDto.getDomain());
		findUser.setIntroduce(updateDto.getIntroduce());

		return new UserUpdateResponseDto(findUser.getId(), findUser.getName(), findUser.getNickname(),
			findUser.getPicture(), findUser.getDomain(), findUser.getIntroduce());
	}

	//조회
	public List<UserResponseDto> findAllUser() {
		List<User> allUser = userRepository.findAll();
		List<UserResponseDto> collect = allUser.stream()
			.map(UserResponseDto::new)
			.collect(Collectors.toList());

		return collect;
	}

	//삭제
	@Transactional
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}


	//==검증 로직==//
	public void validateDuplicateDomain(String domain) {
		if (userRepository.findByDomain(domain).isPresent()) {
			throw new IllegalUserException("이미 존재하는 도메인입니다.");
		}
	}

	public void validateDuplicateEmail(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new IllegalUserException("이미 존재하는 이메일입니다.");
		}
	}
}
