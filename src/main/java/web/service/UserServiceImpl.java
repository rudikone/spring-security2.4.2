package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.models.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User show(int id) {
        return userDAO.show(id);
    }

    @Override
    @Transactional
    public void update(int id, User updateUser) {
        User userToBeUpdated = userDAO.show(id);
        userToBeUpdated.setName(updateUser.getName());
        userToBeUpdated.setPassword(updateUser.getPassword());
        userToBeUpdated.setRoles(updateUser.getRoles());
        userDAO.update(userToBeUpdated);
    }

    @Override
    @Transactional
    public void save(User person) {
        userDAO.save(person);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDAO.delete(userDAO.show(id));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.getUserByName(s);
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }
}
