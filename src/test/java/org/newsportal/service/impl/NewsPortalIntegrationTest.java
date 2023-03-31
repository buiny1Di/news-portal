package org.newsportal.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.newsportal.database.entity.News;
import org.newsportal.database.entity.User;
import org.newsportal.database.repository.NewsRepository;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.database.repository.impl.UserRepositoryImpl;
import org.newsportal.service.NewsService;
import org.newsportal.service.UserService;
import org.newsportal.service.mapper.NewsMapper;
import org.newsportal.service.mapper.UserMapper;
import org.newsportal.service.mapper.impl.UserMapperImpl;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Properties;

@Testcontainers
public class NewsPortalIntegrationTest {
    @Container
    private static final MySQLContainer mysql = new MySQLContainer(DockerImageName.parse("mysql"));

    private static UserService userService;
    private static UserMapper userMapper;
    private static UserRepository userRepository;

    private static NewsService newsService;
    private static NewsMapper newsMapper;
    private static NewsRepository newsRepository;

    private static SessionFactory sessionFactory;
    private static Properties properties;

    private static org.newsportal.service.model.User modelUser;

    @BeforeAll
    private static void setUp() {
        mysql.start();
        properties = new Properties();
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.password", mysql.getPassword());
        properties.put("hibernate.connection.url", mysql.getJdbcUrl());
        properties.put("hibernate.connection.username", mysql.getUsername());
        properties.put("show_sql", true);

        sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(News.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        userMapper = new UserMapperImpl();

        userRepository = new UserRepositoryImpl(sessionFactory);
        userService = new UserServiceImpl(userRepository , userMapper);

        modelUser = new org.newsportal.service.model.User();

    }

    @Test
    public void newsPortalIntegrationTest() {
        modelUser.setLogin("Test login");
        modelUser.setPassword("Test Password");

        userService.addUser(modelUser);
        org.newsportal.service.model.User acquiredUser = userService.getUserById(modelUser.getLogin()).get();

        Assertions.assertEquals(acquiredUser, modelUser);

        org.newsportal.service.model.News news = new org.newsportal.service.model.News();

        news.setContent("test content");
        news.setTitle("test title");
        news.setUser(modelUser);

        newsService.addNews(news);



    }

    @AfterAll
    private static void shutDown() {
        mysql.stop();
    }
}
