package org.example.step3;

import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UseStatement {
    public static void main(String[] args) {
//        String id = "admin";
//        String id = "admin2";
        String id = "1234' OR '1'='1";
        try (Connection conn = DBUtil.getConnection()) {
//            String query = "SELECT * FROM users";
//            String query = "SELECT * FROM users WHERE id = 'admin'";
            String query = "SELECT * FROM users WHERE id = '%s'".formatted(id);
            // "SELECT * FROM users WHERE id = '1234' OR '1'='1'"
            Statement stmt = conn.createStatement(); // SQL Injection의 가능성 때문에 유저의 입력이 있는 부분은 Statement 사용 X
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // rs -> 컬럼 이름으로 가지고 있음
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("조회 실패 : %s".formatted(e.getMessage()));
            throw new RuntimeException(e);
        }
    }
}
