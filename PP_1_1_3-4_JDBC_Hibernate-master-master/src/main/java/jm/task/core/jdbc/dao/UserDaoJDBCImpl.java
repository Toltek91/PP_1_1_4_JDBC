package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastname VARCHAR(20), age INT(2))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStat = connection.prepareStatement("INSERT INTO users ( name, lastname, age) VALUES (?,?,?)")) {
            preparedStat.setString(1, name);
            preparedStat.setString(2, lastName);
            preparedStat.setByte(3, age);
            preparedStat.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement preparedStat = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStat.setLong(1, id);
            int hasResult = preparedStat.executeUpdate();
            if (hasResult == 1) {
                System.out.println("Пользователь с id " + id + " удален из базы");
            } else {
                System.out.println("Пользователь с id " + id + " не найден в базе");
            }
        } catch (SQLException sqlEx) {
            throw new RuntimeException("Error removing user with ID: " + id, sqlEx);
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);

            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return userList;

    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

