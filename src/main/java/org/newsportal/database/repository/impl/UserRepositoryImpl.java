package org.newsportal.database.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.newsportal.database.entity.News;
import org.newsportal.database.entity.User;
import org.newsportal.database.repository.UserRepository;
import org.newsportal.database.util.HibernateUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepository {
    EntityManagerFactory entityManagerFactory;
    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        /*
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> personCriteria = cb.createQuery(User.class);
        Root<User> userRoot = personCriteria.from(User.class);
        personCriteria.select(userRoot);
        em.createQuery(personCriteria)
                .getResultList()
                .forEach(System.out::println);
          */



        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM User", User.class);
            List<User> users = query.getResultList();
            session.getTransaction().commit();
            sessionFactory.close();
            return users;
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
            User newUser = session.get(User.class, id);
            newUser.setLogin(user.getLogin());
            newUser.setPassword(user.getPassword());
            newUser.setNews(user.getNews());
            session.getTransaction().commit();
            return newUser;
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
