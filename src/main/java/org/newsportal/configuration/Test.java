package org.newsportal.configuration;

import org.newsportal.service.NewsService;
import org.newsportal.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        NewsService newsService = (NewsService) applicationContext.getBean("newsService");
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(newsService.getAll());
        System.out.println(userService.getAll());

    }
}
