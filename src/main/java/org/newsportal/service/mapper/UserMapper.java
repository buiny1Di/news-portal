package org.newsportal.service.mapper;

import org.newsportal.database.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> mapToDatabase(List<org.newsportal.service.model.User> source);
    List<org.newsportal.service.model.User> mapToService(List<User> source);
    User mapToDatabase(org.newsportal.service.model.User source);
    org.newsportal.service.model.User mapToService(User source);
}
