package org.newsportal.service.model;

import org.newsportal.service.model.News;

import javax.persistence.*;
import java.util.List;
public class User {

        private String id;
        private String login;
        private String password;
        private List<News> news;

        public User() {
        }

        public User(String id, String login, String password, List<News> news) {
            this.id = id;
            this.login = login;
            this.password = password;
            this.news = news;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<News> getNews() {
            return news;
        }

        public void setNews(List<News> news) {
            this.news = news;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", news=" + news.toString() +
                    '}';
        }
    }

