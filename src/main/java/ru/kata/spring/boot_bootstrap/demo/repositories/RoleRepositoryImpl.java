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
    public Set<Role> getList() {
       return (entityManager.createQuery("select s from Role s", Role.class).getResultList()).stream().collect(Collectors.toSet());
    }

    @Override
    public Set<Role> getAllRoles() {
        return (entityManager.createQuery("from Role", Role.class).getResultList()).stream().collect(Collectors.toSet());
    }

    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void deleteRole(Long id) {
        entityManager.remove(getRole(id));
    }

    @Override
    public void editRole(Role role) {
        entityManager.merge(role);
    }
    @Override
    public Set<Role> listByName(Set<String> name) {
        return  entityManager.createQuery("select u FROM Role u WHERe u.role in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList()
                .stream()
                .collect(Collectors.toSet());
    }
    @Override
    public Role findByName(String name) {
        return entityManager.createQuery("select u FROM Role u WHERe u.role = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

}


