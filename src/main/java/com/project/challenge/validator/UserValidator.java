package com.project.challenge.validator;

import com.project.challenge.domain.user.UserStatus;
import com.project.challenge.dto.UserDto;
import com.project.challenge.repository.UserRepository;
import com.project.challenge.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto.addUser userDto = (UserDto.addUser) target;
        //활동중인 회원의 아이디와 중복되는지 체크한다.
        if (userService.duplicateCheckUserId(userDto)) {
            errors.rejectValue("userId", "Duplicate.user.userId",
                    new Object[]{userDto.getUserId()},"사용중인 아이디");
        }
        //활동중인 회원의 이메일과 중복되는지 체크한다.
        if (userService.duplicateCheckEmail(userDto)) {
            errors.rejectValue("email", "Duplicate.user.email",
                    new Object[]{userDto.getEmail()},"사용중인 이메일");
        }
        //활동중인 회원의 닉네임과 중복되는지 체크한다.
        if (userService.duplicateCheckUsername(userDto)) {
            errors.rejectValue("username", "Duplicate.user.username",
                    new Object[]{userDto.getUsername()},"사용중인 닉네임");
        }
    }
}
