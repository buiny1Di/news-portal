package org.newsportal.configuration;

import org.hibernate.SessionFactory;
import org.newsportal.database.repository.NewsRepository;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.database.repository.impl.NewsRepositoryImpl;
import org.newsportal.database.repository.impl.UserRepositoryImpl;
import org.newsportal.database.util.HibernateUtil;
import org.newsportal.service.NewsService;
import org.newsportal.service.UserService;
import org.newsportal.service.impl.NewsServiceImpl;
import org.newsportal.service.impl.UserServiceImpl;
import org.newsportal.service.mapper.NewsMapper;
import org.newsportal.service.mapper.UserMapper;
import org.newsportal.service.mapper.impl.NewsMapperImpl;
import org.newsportal.service.mapper.impl.UserMapperImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@EnableWebMvc
@ComponentScan("org.newsportal")
public class AppConfiguration {
    @Bean
    public SessionFactory sessionFactory() {
        return HibernateUtil.getSessionFactory();
    }
    @Bean
    public NewsMapper newsMapper() {
        return new NewsMapperImpl();
    }
    @Bean
    public UserMapper userMapper() {return new UserMapperImpl();}

    @Bean
    public NewsRepository newsRepository(SessionFactory sessionFactory) {
        return new NewsRepositoryImpl(sessionFactory);
    }
    @Bean
    public UserRepository userRepository(SessionFactory sessionFactory) {
        return new UserRepositoryImpl(sessionFactory);
    }

    @Bean
    public NewsService newsService() {
        return new NewsServiceImpl(newsRepository(sessionFactory()), newsMapper());
    }

    @Bean
    public UserService userService() {return new UserServiceImpl(userRepository(sessionFactory()), userMapper()); }
}
