package com.project.challenge.controller;

import com.project.challenge.config.session.SessionConst;
import com.project.challenge.domain.user.UserDto;
import com.project.challenge.exception.LoginFailException;
import com.project.challenge.service.user.UserService;
import com.project.challenge.validator.addUserFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final addUserFormValidator addUserFormValidator;

    /**
     * Controller 메서드 실행할 때마다 Validator를 호출한다.
     * 모든 Controller 메서드는 검증을 거치게 된다.
     */
/*    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(addUserFormValidator);
    }*/
    @GetMapping("/join")
    public String addUserForm(@ModelAttribute("user") UserDto.addUser userDto) {
        return "users/addUserForm";
    }

    @PostMapping("/join")
    public String joinUser(@Valid @ModelAttribute("user") UserDto.addUser userDto, BindingResult result) {
        // 예외가 발생하면 회원가입 폼으로 redirect
        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "users/addUserForm";
        }
        userService.userSave(userDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String LoginUserForm(@ModelAttribute("user") UserDto.loginUser userDto) {
        return "users/loginForm";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("user") UserDto.loginUser userDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "users/loginForm";
        }

        Optional<UserDto.loginUser> loginUser = userService.loginUser(userDto);
        //가입된 회원인지 체크
        if(loginUser.isEmpty()) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "users/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:/";
    }
}
