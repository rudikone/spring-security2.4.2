package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.models.Person;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public List<Person> index() {
        return userDAO.index();
    }

    @Override
    @Transactional
    public Person show(int id) {
        return userDAO.show(id);
    }

    @Override
    @Transactional
    public void update(int id, Person updatePerson) {
        userDAO.update(id, updatePerson);
    }

    @Override
    @Transactional
    public void save(Person person) {
        userDAO.save(person);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDAO.delete(id);
    }
}
