package ru.kata.spring.boot_bootstrap.demo.repositories;

import ru.kata.spring.boot_bootstrap.demo.model.Role;

import java.util.Set;


public interface RoleRepository {
    boolean add(Role user);

    Set<Role> getAllRoles();

}
