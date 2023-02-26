package org.newsportal.database.repository;

import org.newsportal.database.entity.News;

import java.util.List;

public interface NewsRepository {
        List<News> findAll();

        News findNewsById(String id);

        News findNewsByTitle(String title);

        void createNews(News news);

        News updateNews(String id, News news);

        void deleteNews(String id);
}
