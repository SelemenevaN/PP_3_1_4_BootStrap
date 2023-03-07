package ru.kata.spring.boot_bootstrap.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_bootstrap.demo.model.Role;
import ru.kata.spring.boot_bootstrap.demo.repositories.UserRepository;
import ru.kata.spring.boot_bootstrap.demo.model.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Set<User> allUsers() {return userRepository.getList();}

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.add(user);
    }

    @Override
    public User showUser(Long id) {return userRepository.showUser(id);}

    @Transactional
    @Override
    public void deleteUser(Long id) {userRepository.deleteUser(id);}

    @Override
    public User getUser(Long id) {return userRepository.getUser(id);}

    @Override
    public void editUser(Long id, User user) {}
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = (User)userRepository.getUser(email);
        user.getRoles().size();
        return user;
    }
    @Override
    @Transactional
    public User getUserByEmail(String email)throws UsernameNotFoundException {
        User user = (User)userRepository.getUserByEmail(email);
        user.getRoles().size();
        return user;
    }

    @Transactional
    @Override
    public boolean updateUser(User updatedUser) {
        boolean checkNewEmail = false;
        if (userRepository.getUserByEmail(updatedUser.getEmail())==null)
            checkNewEmail = true;
        userRepository.add(updatedUser);
        return checkNewEmail;
    }

      private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }
}


