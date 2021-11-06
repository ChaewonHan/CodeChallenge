package com.project.challenge.service.user;

import com.project.challenge.domain.user.User;
import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.dto.UserDto;
import com.project.challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void userSave(UserDto.addUser userDto){
        String encodePw = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePw);
        User user = userDto.toEntity();
        userRepository.save(user);
    }

    public boolean duplicateCheckUserId(UserDto.addUser userDto) {
        return userRepository.existsByUserIdAndUserStatus(userDto.getUserId(), UserStatus.ACTIVE);
    }

    public boolean duplicateCheckEmail(UserDto.addUser userDto) {
        return userRepository.existsByEmailAndUserStatus(userDto.getEmail(), UserStatus.ACTIVE);
    }

    public boolean duplicateCheckUsername(UserDto.addUser userDto) {
        return userRepository.existsByUsernameAndUserStatus(userDto.getUsername(), UserStatus.ACTIVE);
    }
}
