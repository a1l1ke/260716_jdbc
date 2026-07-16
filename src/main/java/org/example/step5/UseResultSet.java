package org.example.step5;

import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UseResultSet {
    record MyUser(String id, String name, int age) {
    }

    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
//            String query = "SELECT id, name, age FROM users";
            String query = "SELECT id AS user_id, name AS user_name, age AS user_age FROM users";
            // ALIAS?
            // ResultSet은 SELECT QUERY의 결과물을 1줄씩 가져오는 기능
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { // 다음 줄로 (일종의 큐)
//                System.out.println("id : " + rs.getString("id"));
//                System.out.println("name : " + rs.getString("name"));
//                System.out.println("age : " + rs.getInt("age"));
                System.out.println(rs); // 객체
//                System.out.println("id : %s, name : %s, age : %d".formatted(
//                        rs.getString("id"),
//                        rs.getString("name"),
//                        rs.getInt("age")));
                // SELECT id(1), name(2), age(3) FROM users
//                System.out.println("id : %s, name : %s, age : %d".formatted(
//                        rs.getString(1),
//                        rs.getString(2),
//                        rs.getInt(3)));
//                System.out.println("id : %s, name : %s, age : %d".formatted(
//                        rs.getString("user_id"),
//                        rs.getString("user_name"),
//                        rs.getInt("user_age")));
                System.out.println(new MyUser(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getInt("user_age")));
            }
        } catch (SQLException e) {
            System.out.println("조회 실패 : %s".formatted(e.getMessage()));
            throw new RuntimeException(e);
        }
    }
}
