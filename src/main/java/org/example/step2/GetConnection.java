package org.example.step2;

import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class GetConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.getConnection();
            // NullPointerException -> .env 환경변수 주입이 X
            System.out.println("DB 연결 성공 : %s".formatted(conn.getMetaData().getURL()));
        } catch (SQLException e) {
            System.out.println("DB 연결 실패");
            throw new RuntimeException(e);
        }
    }
}
