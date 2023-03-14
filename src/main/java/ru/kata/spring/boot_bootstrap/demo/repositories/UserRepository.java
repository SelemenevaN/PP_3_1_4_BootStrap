package ru.kata.spring.boot_bootstrap.demo.repositories;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_bootstrap.demo.model.User;

import java.util.Set;

public interface UserRepository {
    void add(User user);
    Set<User> getList();
    User getUser(Long id);
    void deleteUser(Long id);
    void editUser(User user);
    UserDetails getUser(String email);
    User showUser(Long id);
    User getUserByEmail(String email);
}