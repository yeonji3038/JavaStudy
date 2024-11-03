package com.example.study_1_CRUD.controller;

import com.example.study_1_CRUD.service.UserService;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserForm userForm) {
        return "Users/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Users/signup";
        }

        if (!userForm.getPassword1().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "패스워드가 일치하지 않습니다.");
            return "Users/signup";
        }

        try {
            userService.create(userForm.getUsername(), userForm.getPassword1());
        } catch (Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "Users/signup";
        }

        return "redirect:/boards";
    }

    @GetMapping("/login")
    public String login() {
        return "Users/login";
    }

}
