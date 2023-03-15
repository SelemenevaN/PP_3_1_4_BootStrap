package ru.kata.spring.boot_bootstrap.demo.util;

import org.springframework.core.convert.converter.Converter;
import ru.kata.spring.boot_bootstrap.demo.model.Role;

public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String id) {
        Role role = new Role();
        role.setId(Long.valueOf(id));
        return role;
    }

}

