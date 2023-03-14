package ru.kata.spring.boot_bootstrap.demo.repositories;

import ru.kata.spring.boot_bootstrap.demo.model.Role;

import java.util.Set;


public interface RoleRepository {
    boolean add(Role user);

    Role getRole(Long id);

    Set<Role> getList();

    Set<Role> getAllRoles();

    void deleteRole(Long id);

    void editRole(Role role);

    Set<Role> listByName(Set<String> name);
}
