package org.newsportal.service.impl;

import org.newsportal.service.model.News;
import org.newsportal.database.repository.NewsRepository;
import org.newsportal.service.NewsService;
import org.newsportal.service.mapper.NewsMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    @Override
    public Optional<List<News>> getAll() {
        return Optional.ofNullable(newsMapper.mapToService(newsRepository.findAll()));
    }

    @Override
    public Optional<News> getNewsById(String id) {
        return Optional.ofNullable(newsMapper.mapToService(newsRepository.findNewsById(id)));
    }

    @Override
    public Optional<News> getNewsByTitle(String title) {
        return Optional.ofNullable(newsMapper.mapToService(newsRepository.findNewsByTitle(title)));
    }

    @Override
    public void addNews(News news) {
       newsRepository.createNews(newsMapper.mapToDatabase(news));
    }

    @Override
    public Optional<News> changeNews(String id, News news) {
        return Optional.ofNullable(newsMapper.mapToService(
                newsRepository.updateNews(id, newsMapper.mapToDatabase(news))));
    }

    @Override
    public void removeNews(String id) {
        newsRepository.deleteNews(id);
    }
}
