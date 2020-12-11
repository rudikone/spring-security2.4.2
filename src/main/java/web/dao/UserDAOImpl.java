package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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
        personToBeUpdated.setName(updatePerson.getName());
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
//    private static int PEOPLE_COUNT;
//    private List<Person> people;
//
//    {
//        people = new ArrayList<>();
//
//        people.add(new Person(++PEOPLE_COUNT, "Tom"));
//        people.add(new Person(++PEOPLE_COUNT, "Bob"));
//        people.add(new Person(++PEOPLE_COUNT, "Mike"));
//        people.add(new Person(++PEOPLE_COUNT, "Katy"));
//    }
//
//    public List<Person> index(){
//        return people;
//    }
//
//    public Person show(int id){
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
//    }
//
//    public void save(Person person){
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
//    }
//
//    public void update(int id, Person updatePerson){
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatePerson.getName());
//    }
//
//    public void delete(int id){
//        people.removeIf(p -> p.getId() == id);
//    }
