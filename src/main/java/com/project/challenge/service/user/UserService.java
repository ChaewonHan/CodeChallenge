package com.project.challenge.service.user;

import com.project.challenge.config.session.SessionConst;
import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.domain.user.UserDto;
import com.project.challenge.exception.DuplicateEmailException;
import com.project.challenge.exception.DuplicateUsernameException;
import com.project.challenge.exception.LoginFailException;
import com.project.challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void userSave(UserDto.addUser userDto){
        if (duplicateCheckEmail(userDto.getEmail())) {
            throw new DuplicateEmailException("사용중인 이메일 입니다.");
        }
        if (duplicateCheckUsername(userDto.getUsername())) {
            throw new DuplicateUsernameException("사용중인 닉네임 입니다.");
        }
        String encodePw = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePw);
        User user = userDto.toEntity();
        userRepository.save(user);
    }

    @Transactional
    public void loginUser(UserDto.loginUser userDto) {
        String password = findEmail(userDto.getEmail()).getPassword();

        if (passwordEncoder.matches(userDto.getPassword(), password)) {
            log.info("로그인 성공");
            userDto.setPassword(password);
        } else {
            throw new LoginFailException("이메일 또는 비밀번호가 잘못 입력 되었습니다.");
        }
    }

    @Transactional
    public User findEmail(String email) {
        return userRepository.findByEmailAndUserStatus(email, UserStatus.ACTIVE)
                .orElseThrow(() -> new LoginFailException("이메일 또는 비밀번호가 잘못 입력 되었습니다."));
    }

    @Transactional(readOnly = true)
    public boolean duplicateCheckEmail(String email) {
        return userRepository.existsByEmailAndUserStatus(email, UserStatus.ACTIVE);
    }

    @Transactional(readOnly = true)
    public boolean duplicateCheckUsername(String username) {
        return userRepository.existsByUsernameAndUserStatus(username, UserStatus.ACTIVE);
    }
}
