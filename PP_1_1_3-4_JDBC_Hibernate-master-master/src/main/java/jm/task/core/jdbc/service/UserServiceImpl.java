package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao UserDaoJDBCImpl= new UserDaoJDBCImpl();


    @Override
    public void createUsersTable() {
        try {
            UserDaoJDBCImpl.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void dropUsersTable() {
        try {
            UserDaoJDBCImpl.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void saveUser(String name, String lastName, byte age)  {
        try {
            UserDaoJDBCImpl.saveUser(name, lastName, age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем – " + name +" добавлен в базу данных ");
    }
    @Override
    public void removeUserById(long id)  {
        try {
            UserDaoJDBCImpl.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public List<User> getAllUsers()  {
        List<User> users;
        try {
            users = UserDaoJDBCImpl.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }
    @Override
    public void cleanUsersTable()  {
        try {
            UserDaoJDBCImpl.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
