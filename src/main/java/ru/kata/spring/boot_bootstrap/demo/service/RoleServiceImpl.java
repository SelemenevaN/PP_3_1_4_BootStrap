package ru.kata.spring.boot_bootstrap.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_bootstrap.demo.repositories.RoleRepository;
import ru.kata.spring.boot_bootstrap.demo.model.Role;

import java.util.Set;

@Transactional(readOnly = true)
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public boolean add(Role role) {
        roleRepository.add(role);
        return true;
    }

    @Override
    public Set<Role> getList() {
        return roleRepository.getList();
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.getRole(id);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteRole(id);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        roleRepository.editRole(role);
    }

    @Override
    public Set<Role> listByRole(Set<String> name) {return roleRepository.listByName(name);}
}
