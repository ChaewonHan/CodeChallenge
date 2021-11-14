package com.project.challenge.controller;

import com.project.challenge.config.session.SessionConst;
import com.project.challenge.domain.user.UserDto;
import com.project.challenge.exception.DuplicateEmailException;
import com.project.challenge.exception.DuplicateUsernameException;
import com.project.challenge.exception.LoginFailException;
import com.project.challenge.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @GetMapping("/join")
    public String addUserForm(@ModelAttribute("user") UserDto.addUser userDto) {
        return "users/addUserForm";
    }

    @PostMapping("/join")
    public String joinUser(@Valid @ModelAttribute("user") UserDto.addUser userDto, BindingResult result, HttpServletRequest request) {
        // 예외가 발생하면 회원가입 폼으로 redirect
        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "users/addUserForm";
        }

        try {
            userService.userSave(userDto);
        } catch (DuplicateEmailException e) {
            result.addError(new FieldError("field-error", "email", e.getMessage()));
            return "users/addUserForm";
        } catch (DuplicateUsernameException e) {
            result.addError(new FieldError("field-error", "username", e.getMessage()));
            return "users/addUserForm";
        }
        createSession(userDto.getEmail(), request);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String LoginUserForm(@ModelAttribute("user") UserDto.loginUser userDto, BindingResult result) {
        return "users/loginForm";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("user") UserDto.loginUser userDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "users/loginForm";
        }

        try {
            userService.loginUser(userDto);
        } catch (LoginFailException e) {
            result.addError(new FieldError("field-error", "email", e.getMessage()));
            return "users/loginForm";
        }

        createSession(userDto.getEmail(), request);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            log.info("로그아웃");
            session.invalidate();
        }
        return "redirect:/";
    }

    private void createSession(String email, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_USER, email);
    }
}
