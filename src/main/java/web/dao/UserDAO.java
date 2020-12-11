package web.dao;

import web.models.Person;

import java.util.List;

public interface UserDAO {

    public List<Person> index();
    public Person show(int id);
    public void update(int id, Person updatePerson);
    public void save(Person person);
    public void delete(int id);
}
