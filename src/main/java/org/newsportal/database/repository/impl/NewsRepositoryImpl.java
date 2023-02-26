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
        return null;
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
        return null;
    }

    @Override
    public void deleteNews(String id) {

    }

    public User findUserById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    public static void main(String[] args) {
        NewsRepositoryImpl newsRepository = new NewsRepositoryImpl();
//        News news = new News();
//        news.setTitle("test title");
//        news.setContent("test content");
//        User user = newsRepository.findUserById("7");
//        news.setUser(user);
//        newsRepository.createNews(news);
        System.out.println(newsRepository.findAll());
        System.out.println(newsRepository.findNewsByTitle("test title"));
    }
}
