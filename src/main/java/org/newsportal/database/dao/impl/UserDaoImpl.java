package org.newsportal.database.dao.impl;

import org.newsportal.database.dao.entity.News;
import org.newsportal.database.dao.entity.User;
import org.newsportal.database.dao.util.ConnectionPool;
import org.newsportal.database.dao.util.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {
    private Connection connection;

    public UserDaoImpl() {
        try {
            this.connection = ConnectionPool.getConnectionPool().getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUser(long id) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NEWS WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = new User();
            user.setId(resultSet.getString("id"));
            user.setUsername(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findAllUser() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NEWS")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return userList;
    }

    @Override
    public User createUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS (id, login, password) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate(); //Уточнить

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(long id, User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USERS SET login = ?, password = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, String.valueOf(id));
            preparedStatement.executeQuery();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
