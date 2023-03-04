package org.newsportal.database.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.newsportal.database.entity.News;
import org.newsportal.database.entity.User;
import org.newsportal.database.repository.NewsRepository;
import org.newsportal.database.util.HibernateUtil;

import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {
    private final SessionFactory sessionFactory;

    public NewsRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<News> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM News", News.class).getResultList();
        }
    }

    @Override
    public News findNewsById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(News.class, id);
        }
    }

    @Override
    public News findNewsByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM News where title LIKE " + "'" + title + "'", News.class).getSingleResult();
        }
    }

    @Override
    public void createNews(News news) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(news);
            session.getTransaction().commit();
        }
    }

    @Override
    public News updateNews(String id, News news) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            News n = session.get(News.class, id);
            n.setTitle(news.getTitle());
            n.setContent(news.getContent());
            n.setUser(news.getUser());
            session.save(n);
            session.getTransaction().commit();
            return n;
        }
    }

    @Override
    public void deleteNews(String id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            News news = session.get(News.class, id);
            session.delete(news);
            session.getTransaction().commit();
        }
    }
    public static void main(String[] args) {
        NewsRepositoryImpl newsRepository = new NewsRepositoryImpl();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
       /* News news = new News();
        news.setTitle("test supertest");
        news.setContent("test supercontent");
        news.setUser(userRepository.findUserById("7"));
        newsRepository.updateNews("9" , news);
        newsRepository.deleteNews("9");
        User user = userRepository.findUserById("7");*/
       // userRepository.deleteUser("10");
        User user = new User();
        user.setLogin("Annia");
        user.setPassword("11111");

        userRepository.updateUser("9", user);

        //System.out.println(newsRepository.findAll());
       // System.out.println(newsRepository.findNewsByTitle("test title"));
    }
}
