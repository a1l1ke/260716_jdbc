package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private DBUtil() {
    } // 생성자 X -> static한 값들만 사용할 예정

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("URL = " + URL);
//        System.out.println("USER = " + USER);
//        System.out.println("PASSWORD = " + PASSWORD.substring(0, 5) + "...");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
