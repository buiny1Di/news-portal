package org.newsportal.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        Mockito.verify(newsMapper).mapToService(any(List.class));
        Mockito.verify(newsRepository).findAll();
    }

    @Test
    void getNewsById() {
        newsService.getNewsById("1");
        Mockito.verify(newsRepository).findNewsById("1");
        Mockito.verify(newsMapper).mapToService(newsRepository.findNewsById("1"));

        //Mockito.when(newsRepository.findNewsById("1")).thenReturn(news);


    }

    @Test
    void getNewsByTitle() {
        newsService.getNewsByTitle("Race");
        Mockito.verify(newsRepository).findNewsByTitle("Race");
        Mockito.verify(newsMapper).mapToService(newsRepository.findNewsByTitle("Race"));
    }

    @Test
    void addNews() {
        newsService.addNews(news);
        org.newsportal.database.entity.News added = Mockito.verify(newsMapper).mapToDatabase(news);
        Mockito.verify(newsRepository).createNews(added);
    }

    @Test
    void changeNews() {
        News newsFuture = new News();
        newsFuture.setId("2");
        newsFuture.setTitle("Football");
        newsFuture.setContent("Argentina wins");

        User user = new User("2", "Oleg", "Borisov", Collections.singletonList(newsFuture));
        newsFuture.setUser(user);

        newsRepository.updateNews("1", newsMapper.mapToDatabase(newsFuture));
        org.newsportal.database.entity.News updatedNews = Mockito.verify(newsMapper).mapToDatabase(newsFuture);
        Mockito.verify(newsRepository).updateNews("1", updatedNews);
        //Mockito.doReturn(newsFuture).when(newsRepository).findNewsById("1");



        //Assertions.assertEquals(updatedNews, newsFuture);
    }

    @Test
    void removeNews() {
        newsRepository.deleteNews("1");
        Mockito.verify(newsRepository).deleteNews("1");
    }
}