package ru.kata.spring.boot_bootstrap.demo.service;


import ru.kata.spring.boot_bootstrap.demo.model.Role;
import java.util.Set;

public interface RoleService {

    boolean add(Role role);
    Set<Role> getList();
    Role getRole(Long id);
    void deleteRole(Long id);
    void editRole(Role role);
    Set<Role> listByRole(Set<String> name);
    Set<Role> getAllRoles();
}