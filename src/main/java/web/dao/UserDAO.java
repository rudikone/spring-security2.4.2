package web.dao;

import web.models.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    public User show(int id);

    public void update(User userToBeUpdated);

    public void save(User person);

    public void delete(User user);

    public User getUserByName(String name);
}
