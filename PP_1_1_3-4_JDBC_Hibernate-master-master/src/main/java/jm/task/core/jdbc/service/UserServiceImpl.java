package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao UserDaoJDBCImpl = new UserDaoJDBCImpl();


    @Override
    public void createUsersTable() {
        UserDaoJDBCImpl.createUsersTable();

    }

    @Override
    public void dropUsersTable() {
        UserDaoJDBCImpl.dropUsersTable();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных ");
    }

    @Override
    public void removeUserById(long id) {
        UserDaoJDBCImpl.removeUserById(id);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        users = UserDaoJDBCImpl.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        UserDaoJDBCImpl.cleanUsersTable();
    }
}
