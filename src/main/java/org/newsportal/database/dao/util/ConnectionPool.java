package org.newsportal.database.dao.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static Properties properties;
    static {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\User\\IdeaProjects\\news-portal\\src\\main\\resources\\database.property")) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final static String USERNAME = properties.getProperty("user");
    private final static String PASSWORD = properties.getProperty("password");
    private final static String URL = properties.getProperty("url");
    private final static int MAX_CONNECTIONS = Integer.parseInt(properties.getProperty("max_connections"));
    private final static int MIN_CONNECTIONS = Integer.parseInt(properties.getProperty("min_connections"));




    private static ConnectionPool instance;

    private BlockingQueue<Connection> queue = new ArrayBlockingQueue<>(MAX_CONNECTIONS);


    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0; i < MIN_CONNECTIONS; i++) {
                queue.put(createConnection());
            }
        } catch (ClassNotFoundException | InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getConnectionPool() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public synchronized Connection getConnection() throws InterruptedException {
        return queue.take();
    }

    public synchronized void returnConnection(Connection connection) throws InterruptedException {
        queue.put(connection);
    }
}
