package org.newsportal.database.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.newsportal.database.entity.News;
import org.newsportal.database.entity.User;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.database.util.HibernateUtil;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Users", User.class).getResultList();
        }

    }

    @Override
    public User findUserById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public void createUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User updateUser(String id, User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User u = session.get(User.class, id);
            u.setLogin(user.getLogin());
            u.setPassword(user.getPassword());
            u.setNews(user.getNews());
            session.save(u);
            session.getTransaction().commit();
            return u;
        }

    }

    @Override
    public void deleteUser(String id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}