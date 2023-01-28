package org.newsportal.database.dao;

import org.newsportal.database.entity.News;

import java.util.List;

public interface NewsDao {
    public List<News> findAll();
    public News findNewsById(Long id);
    public News findNewsByTitle(String title);
    public News createNews(News news);
    public News updateNews(Long id, News news);
    public void deleteNews(Long id);
}
