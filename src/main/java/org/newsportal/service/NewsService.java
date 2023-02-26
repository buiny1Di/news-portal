package org.newsportal.service;

import org.newsportal.service.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    Optional<List<News>> getAll();

    Optional<News> getNewsById(String id);

    Optional<News> getNewsByTitle(String title);

    void addNews(News news);

    Optional<News> changeNews(String id, News news);

    void removeNews(String id);
}
