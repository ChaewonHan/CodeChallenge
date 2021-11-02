package com.project.challenge.validator;

import com.project.challenge.dto.UserDto;
import com.project.challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        //활동중인 회원의 아이디와 중복되는지 체크한다.
        if (userRepository.existsByUserIdAndDeleteAccount(userDto.getUserId(), false)) {
            errors.rejectValue("userId", "Duplicate.user.userId");
        }
        //활동중인 회원의 이메일과 중복되는지 체크한다.
        if (userRepository.existsByEmailAndDeleteAccount(userDto.getEmail(), false)) {
            errors.rejectValue("email", "Duplicate.user.email");
        }
        //활동중인 회원의 닉네임과 중복되는지 체크한다.
        if (userRepository.existsByUsernameAndDeleteAccount(userDto.getUsername(), false)) {
            errors.rejectValue("username", "Duplicate.user.username");
        }

    }
}
