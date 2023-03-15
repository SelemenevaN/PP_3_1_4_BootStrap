package ru.kata.spring.boot_bootstrap.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_bootstrap.demo.model.Role;
import ru.kata.spring.boot_bootstrap.demo.model.User;
import ru.kata.spring.boot_bootstrap.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_bootstrap.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class Util {
    private final UserService userService;
    private final RoleServiceImpl roleService;

    public Util(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    public void addFirstUsers() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.add(roleAdmin);
        roleService.add(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);
        User user = new User("user","Jane", "Smith",
                "user@mail.ru", userSet, (byte) 30);
        User admin = new User("admin","John",
                "Smith", "admin@mail.ru", adminSet, (byte)40);

        userService.addUser(user);
        userService.addUser(admin);
        System.out.println(user.toString());
        System.out.println(admin.toString());
    }
}

