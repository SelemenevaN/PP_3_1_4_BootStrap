package ru.kata.spring.boot_bootstrap.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_bootstrap.demo.model.User;
import java.util.Set;

public interface UserService extends UserDetailsService{

    Set<User> allUsers();
    void addUser(User user);
    User showUser(Long id);
    void deleteUser(Long id);
    User getUser(Long id);
    void editUser(Long id, User user);
    UserDetails loadUserByUsername(String username);
    boolean updateUser(User updatedUser);
    User getUserByEmail(String email);
}