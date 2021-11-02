package com.project.challenge.service.user;

import com.project.challenge.domain.user.User;
import com.project.challenge.dto.UserDto;
import com.project.challenge.exception.DuplicateException;
import com.project.challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void userSave(UserDto userDto){
        User user = userDto.toEntity();
        userRepository.save(user);
    }

}
