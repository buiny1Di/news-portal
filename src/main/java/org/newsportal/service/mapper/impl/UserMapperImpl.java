package org.newsportal.service.mapper.impl;

import org.newsportal.database.entity.News;
import org.newsportal.database.entity.User;
import org.newsportal.service.mapper.NewsMapper;
import org.newsportal.service.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public List<User> mapToDatabase(List<org.newsportal.service.model.User> source) {
        return source.stream().map(this::mapToDatabase).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<org.newsportal.service.model.User> mapToService(List<User> source) {
        return source.stream().map(this::mapToService).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public User mapToDatabase(org.newsportal.service.model.User source) {
        if (source == null) {
            return null;
        }

        List<News> news = source.getNews().stream()
                .filter(Objects::nonNull)
                .map(serviceNews -> {
                    News databaseNews = new News();
                    databaseNews.setId(serviceNews.getId());
                    databaseNews.setTitle(serviceNews.getTitle());
                    databaseNews.setContent(serviceNews.getContent());
                    return databaseNews;
                })
                .collect(Collectors.toList());

        return new User(source.getId(), source.getLogin(), source.getPassword(), news);
    }

    @Override
    public org.newsportal.service.model.User mapToService(User source) {
        if (source == null) {
            return null;
        }
        NewsMapper newsMapper = new NewsMapperImpl();
        List<org.newsportal.service.model.News> news = newsMapper.mapToService(source.getNews());
        return new org.newsportal.service.model.User(source.getId(), source.getLogin(), source.getPassword(), news);
    }
}
