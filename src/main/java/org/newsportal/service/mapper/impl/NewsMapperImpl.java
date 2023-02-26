package org.newsportal.service.mapper.impl;

import org.newsportal.database.entity.News;
import org.newsportal.database.entity.User;
import org.newsportal.service.mapper.NewsMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NewsMapperImpl implements NewsMapper {

    @Override
    public List<News> mapToDatabase(List<org.newsportal.service.model.News> source) {
        return source.stream().map(this::mapToDatabase).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<org.newsportal.service.model.News> mapToService(List<News> source) {
        return null;
    }

    @Override
    public News mapToDatabase(org.newsportal.service.model.News source) {
        if (source == null) {
            return null;
        }

        User user = null;
        if (source.getUser() != null) {
            user = new User();
            user.setId(source.getUser().getId());
            user.setLogin(source.getUser().getLogin());
            user.setPassword(source.getUser().getPassword());
        }

        return new News(source.getId(), source.getTitle(), source.getContent(), user);
    }

    @Override
    public org.newsportal.service.model.News mapToService(News source) {
        return null;
    }
}
