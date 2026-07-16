package org.example.step3;

import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UseStatement {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            String query = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
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
