package org.newsportal.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.newsportal.database.repository.NewsRepository;
import org.newsportal.service.mapper.NewsMapper;
import org.newsportal.service.model.News;
import org.newsportal.service.model.User;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {

    @InjectMocks
    private NewsServiceImpl newsService;
    @Mock
    private NewsRepository newsRepository;
    @Mock
    private NewsMapper newsMapper;

    private static News news;

    private static User user;
    @BeforeAll
    public static void init() {
        news = new News("1", "Race" , "Schumaher" , user);
        user = new User("1", "Dima", "B", Collections.singletonList(news));
    }

    @Test
    void getAll() {
        newsService.getAll();
        verify(newsMapper).mapToService(any(List.class));
        verify(newsRepository).findAll();
    }

    @Test
    void getNewsById() {
    }

    @Test
    void getNewsByTitle() {
    }

    @Test
    void addNews() {
    }

    @Test
    void changeNews() {
    }

    @Test
    void removeNews() {
    }
}