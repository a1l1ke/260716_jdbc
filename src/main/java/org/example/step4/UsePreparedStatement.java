package org.example.step4;

import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsePreparedStatement {
    public static void main(String[] args) {
        String id = "1234' OR '1'='1";
        try (Connection conn = DBUtil.getConnection()) {
            // String query = "SELECT * FROM users WHERE id = '%s'".formatted(id);
            String query = "SELECT * FROM users WHERE id = ?";
            // Statement stmt = conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id); // SQL로 인식되는게 아니라 문자 리터럴 (코드가 아닌 텍스트)로 인식이 된다 -> SQL로 해석되지 않음
            // ResultSet rs = stmt.executeQuery(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
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

