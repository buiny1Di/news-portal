package org.newsportal.database.dao.util;

import org.newsportal.database.dao.entity.User;

import java.util.List;

public interface UserDAO {

       User findUser(long id);

        List<User> findAllUser();

        User createUser(User user);

        User updateUser(long id, User user);

        void deleteUser(long id);
}
