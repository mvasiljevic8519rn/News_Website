package rs.raf.demo.repositories.Users;

import rs.raf.demo.entities.User;

import java.util.List;

public interface UserRepository {
    public User findUser(String email);

    public  boolean checkEmail(String email);
    public User getUser(Integer id);
    public User addUser(User user);

    public User editUser(User user);
    public void deleteUser(String email);

    public List<User>allUsers();

    public void ActivateDeactivate(int id);
}
