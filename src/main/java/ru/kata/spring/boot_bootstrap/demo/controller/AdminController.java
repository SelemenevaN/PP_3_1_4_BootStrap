package ru.kata.spring.boot_bootstrap.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_bootstrap.demo.model.Role;
import ru.kata.spring.boot_bootstrap.demo.model.User;
import ru.kata.spring.boot_bootstrap.demo.service.RoleService;
import ru.kata.spring.boot_bootstrap.demo.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping()
    public String getUser(@ModelAttribute("user") User user, Model model, Authentication authentication) {
        model.addAttribute("userList", userService.allUsers());
        User userAuth = (User) authentication.getPrincipal();
        model.addAttribute("admin", userAuth);

        model.addAttribute("users", userService.allUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "adminPanel";
    }

    @PostMapping("/createNew")
    public String saveNewUser(@ModelAttribute("user") User user,
                              @RequestParam(value = "nameRole") String nameRole) {
        Role role = new Role(nameRole);
        roleService.add(role);
        user.setRoles(Set.of(role));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRole") String nameRole) {
        Role role = new Role(nameRole);
        roleService.add(role);
        user.setRoles(Set.of(role));

        if (userService.updateUser(user))
            return "redirect:/login";
        else
            return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Authentication authentication) {
        model.addAttribute("userList", userService.allUsers());
        User user = (User) authentication.getPrincipal();
        model.addAttribute("admin", user);
        // model.addAttribute("user", userService.getUser(id));
        return "adminUserPage";
    }

    @DeleteMapping("{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
