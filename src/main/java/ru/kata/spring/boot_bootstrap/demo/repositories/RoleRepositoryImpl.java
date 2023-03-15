package ru.kata.spring.boot_bootstrap.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_bootstrap.demo.model.Role;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private EntityManager entityManager;

    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean add(Role role) {
        entityManager.persist(role);
        return true;
    }

    @Override
    public Set<Role> getAllRoles() {
        return (entityManager.createQuery("from Role", Role.class)
                .getResultList()).stream().collect(Collectors.toSet());
    }

}


