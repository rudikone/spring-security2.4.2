package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.models.Person;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Component
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> index() {
        List<Person> users = entityManager.createQuery("from Person").getResultList();
        return users;
    }

    @Override
    public Person show(int id) {
        Person person = entityManager.find(Person.class, id);
        return person;
    }

    @Override
    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = entityManager.find(Person.class, id);
        Set<Role> roles = personToBeUpdated.getRoles();
        roles.clear();
        personToBeUpdated.setRoles(roles);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setPassword(updatePerson.getPassword());
        personToBeUpdated.setRoles(updatePerson.getRoles());
        entityManager.merge(personToBeUpdated);
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void delete(int id) {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }
}

