package org.newsportal.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("user", "buiny1Di");
        properties.put("password", "544707074id_R");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_portal", "buiny1Di", "544707074id_R");
            connection.getMetaData().getDriverVersion();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<Integer> intFer = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            intFer.add(10);
        }

        Iterator<Integer> iterator = intFer.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0)
                iterator.remove();
        }

    }



}
