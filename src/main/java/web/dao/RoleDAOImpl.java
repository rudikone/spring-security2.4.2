package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Person;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoleById(Integer id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }

}
