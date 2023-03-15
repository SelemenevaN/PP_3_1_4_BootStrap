package ru.kata.spring.boot_bootstrap.demo.repositories;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_bootstrap.demo.model.User;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(User user) {
        entityManager.merge(user);
    }

    @Override
    public Set<User> getList() {
        return entityManager.createQuery("from User", User.class)
                .getResultList().stream().collect(Collectors.toSet());
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery(
                "DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public UserDetails getUser(String email) {
        return entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email",
                User.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email",
                User.class).setParameter("email", email).getSingleResult();

    }

}



