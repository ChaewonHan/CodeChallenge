package com.project.challenge.service.user;

import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.domain.user.UserDto;
import com.project.challenge.exception.LoginFailException;
import com.project.challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void userSave(UserDto.addUser userDto){
        String encodePw = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePw);
        User user = userDto.toEntity();
        userRepository.save(user);
    }

    public void loginUser(UserDto.loginUser userDto) {
        String password = findByEmail(userDto).getPassword();

        if (passwordEncoder.matches(userDto.getPassword(), password)) {
            log.info("로그인 성공");
            userDto.setPassword(password);
        } else {
            throw new LoginFailException("이메일 또는 비밀번호가 잘못 입력 되었습니다.");
        }

    }

    @Transactional
    public User findByEmail(UserDto.loginUser userDto) {
        return userRepository.findByEmail(userDto.getEmail(), UserStatus.ACTIVE)
                .orElseThrow(() -> new LoginFailException("이메일 또는 비밀번호가 잘못 입력 되었습니다."));
    }

    @Transactional(readOnly = true)
    public boolean duplicateCheckEmail(UserDto.addUser userDto) {
        return userRepository.existsByEmailAndUserStatus(userDto.getEmail(), UserStatus.ACTIVE);
    }

    @Transactional(readOnly = true)
    public boolean duplicateCheckUsername(UserDto.addUser userDto) {
        return userRepository.existsByUsernameAndUserStatus(userDto.getUsername(), UserStatus.ACTIVE);
    }
}
