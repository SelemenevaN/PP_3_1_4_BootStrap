package ru.kata.spring.boot_bootstrap.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import ru.kata.spring.boot_bootstrap.demo.model.User;
import ru.kata.spring.boot_bootstrap.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping()
    public String getUser(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "/userUserPage";
    }
}