package org.newsportal.database.repository;

import org.newsportal.database.entity.News;
import org.newsportal.database.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findUserById(String id);

    void createUser(User user);

    User updateUser(String id, User user);

    void deleteUser(String id);
}

