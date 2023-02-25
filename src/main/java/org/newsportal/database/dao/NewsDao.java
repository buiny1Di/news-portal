package org.newsportal.database.dao;

import org.newsportal.database.dao.entity.News;

import java.util.List;

public interface NewsDao {
    List<News> findAll();
    News findNewsById(Long id);
    News findNewsByTitle(String title);
    News createNews(News news);
    News updateNews(Long id, News news);
    void deleteNews(Long id);
}
