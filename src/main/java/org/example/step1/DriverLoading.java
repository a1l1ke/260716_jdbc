package org.example.step1;

public class DriverLoading {
    public static void main(String[] args) {
        System.out.println("DriverLoading.main");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
