package org.newsportal.database.dao.util;

import org.newsportal.database.dao.entity.News;

import java.util.List;

public interface NewsDao {
    List<News> findAll();
    News findNewsById(long id);
    News findNewsByTitle(String title);
    News createNews(News news);
    News updateNews(long id, News news);
    void deleteNews(long id);
}
