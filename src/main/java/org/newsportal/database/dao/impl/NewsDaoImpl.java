package org.newsportal.database.dao.impl;

import org.newsportal.database.dao.util.NewsDao;
import org.newsportal.database.dao.entity.News;
import org.newsportal.database.dao.entity.User;
import org.newsportal.database.dao.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    private Connection connection;

    public NewsDaoImpl() {
        try {
            this.connection = ConnectionPool.getConnectionPool().getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<News> findAll() {
        List<News> newsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NEWS")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                news.setUserId(resultSet.getString("id"));
                news.setContent(resultSet.getString("content"));
                news.setTitle(resultSet.getString("title"));
                news.setUserId(resultSet.getString("user_id"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return newsList;
    }

    public News findNewsById(Long id) {
        News news = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NEWS WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            news = new News();
            news.setUserId(resultSet.getString("id"));
            news.setContent(resultSet.getString("content"));
            news.setTitle(resultSet.getString("title"));
            news.setUserId(resultSet.getString("user_id"));
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return news;
    }

    public News findNewsByTitle(String title) { //Спросить у Артема насчет Лист
        News news = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NEWS WHERE title = ?")) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getString("id"));
                news.setContent(resultSet.getString("content"));
                news.setTitle(resultSet.getString("title"));
                news.setUserId(resultSet.getString("user_id"));
            }
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public News createNews(News news) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO NEWS (title, content, user_id) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setString(3, news.getUserId());
            preparedStatement.executeUpdate();//Уточнить
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public News updateNews(Long id, News news) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE NEWS SET title = ?, content = ? , user_id = ? WHERE id = ?")) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setString(3, news.getUserId());
            preparedStatement.setString(4, String.valueOf(id));
            preparedStatement.executeQuery();
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteNews(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        NewsDaoImpl dao = new NewsDaoImpl();
        UserDaoImpl dao1 = new UserDaoImpl();
        dao1.createUser(new User("Dima", "password"));
        dao.createNews(new News("Sport", "Hamiltom is wins", "4"));
    }
}


