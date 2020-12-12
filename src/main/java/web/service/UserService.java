package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    public List<User> index();

    public User show(int id);

    public void update(int id, User updatePerson);

    public void save(User person);

    public void delete(int id);

}
