package com.advance.technology.register.controller;

import com.advance.technology.designpatterns.event.service.UserRegisterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final UserRegisterService userRegisterService;

    public RegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }
    @GetMapping("/register")
    public String register(@RequestParam String result){
        userRegisterService.registerUser("张三");
        return result;
    }
}
