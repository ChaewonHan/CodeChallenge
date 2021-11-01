package com.project.challenge.controller;

import com.project.challenge.dto.UserDto;
import com.project.challenge.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String addUserForm(@ModelAttribute("user") UserDto userDto) {
        return "users/addUserForm";
    }

    @PostMapping("/join")
    public String joinUser(@ModelAttribute("user") UserDto userDto, BindingResult result) {
        // 예외가 발생하면 회원가입 폼으로 redirect
        if (result.hasErrors()) {
            return "users/addUserForm";
        }

        userService.userSave(userDto);
        return "redirect:/";
    }
}
