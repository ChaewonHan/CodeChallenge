package com.project.challenge.controller;

import com.project.challenge.dto.UserDto;
import com.project.challenge.service.user.UserService;
import com.project.challenge.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    /**
     * Controller 메서드 실행할 때마다 Validator를 호출한다.
     * 모든 Controller 메서드는 검증을 거치게 된다.
     * */
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/join")
    public String addUserForm(@ModelAttribute("user") UserDto userDto) {
        return "users/addUserForm";
    }

    @PostMapping("/join")
    public String joinUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
        // 예외가 발생하면 회원가입 폼으로 redirect
        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "users/addUserForm";
        }
        userService.userSave(userDto);
        return "redirect:/";
    }
}
