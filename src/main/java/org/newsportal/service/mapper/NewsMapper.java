package org.newsportal.service.mapper;

import org.newsportal.database.entity.News;

import java.util.List;

public interface NewsMapper {
    List<News> mapToDatabase(List<org.newsportal.service.model.News> source);
    List<org.newsportal.service.model.News> mapToService(List<News> source);
    News mapToDatabase(org.newsportal.service.model.News source);
    org.newsportal.service.model.News mapToService(News source);
}
