package org.example.repository;

import org.example.entity.Banchan;
import org.example.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanchanRepository {
    // save (create)
    public void save(Banchan banchan) {
        System.out.println("BanchanRepository.save");
        String query = """
                -- ID는 자동으로 생성되므로 name만 입력
                INSERT INTO
                    banchan (name)
                VALUES
                    (?)
                """;
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("START TRANSACTION"); // 트랜잭션 시작
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, banchan.name());
            pstmt.executeUpdate(); // insert류는 executeUpdate()
            // 1. execute -> transaction, ddl
            // 2. executeQuery -> select/procedure -> ResultSet
            // 3. executeUpdate -> insert/update/delete -> int
            stmt.execute("COMMIT"); // 트랜잭션 커밋(저장)
        } catch (SQLException e) {
            System.out.println("반찬 추가 실패");
            throw new RuntimeException(e);
        }
    }

    // find / findAll (read)
    public Banchan findById(long id) {
        System.out.println("BanchanRepository.findById");
        String query = "SELECT id, name FROM banchan WHERE id = ?";
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, id); // 1번째 자리에 id
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { // 값이 1개니까 있냐/없냐가 중요
                return new Banchan(
                        rs.getLong("id"),
                        rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("반찬 읽기 실패");
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Banchan> findAll() {
        System.out.println("BanchanRepository.findAll");
        List<Banchan> result = new ArrayList<>();
//        String query = "SELECT * FROM banchan";
        String query = "SELECT id, name FROM banchan";
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(
                        new Banchan(
                                rs.getLong("id"),
                                rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("반찬 읽기 실패");
            throw new RuntimeException(e);
        }
//        return null;
        return result;
    }

    // update - persistence
    // 1. save -> save upsert?
    // 2. update -> 별도로 update를 하지 않아도 entity가 변경되면 알아서 업데이트 (JPA)
    public void update(Banchan banchan) {
        System.out.println("BanchanRepository.update");
        // 전체 새로운 데이터를 넣어서 처리
        // 원본을 복제해서 -> 수정할 것만 바꾸고 -> 새롭게 넣어서
        String query = """
                    UPDATE banchan
                        SET name = ?
                        WHERE id = ?
                """;
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("START TRANSACTION"); // 트랜잭션 시작
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, banchan.name());
            pstmt.setLong(2, banchan.id()); // ID를 통해서 같은 데이터를 수정함을 체크
            int result = pstmt.executeUpdate();
            if (result == 0) {
                throw new RuntimeException("반찬 수정 실패");
            }
            stmt.execute("COMMIT"); // 트랜잭션 커밋(저장)
        } catch (SQLException e) {
            System.out.println("반찬 수정 실패");
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {
        System.out.println("BanchanRepository.deleteById");
        String query = """
                    DELETE FROM banchan
                        WHERE id = ?
                """;
        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("START TRANSACTION"); // 트랜잭션 시작
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, id);
            int result = pstmt.executeUpdate();
            if (result == 0) {
                throw new RuntimeException("반찬 삭제 실패");
            }
            stmt.execute("COMMIT"); // 트랜잭션 커밋(저장)
        } catch (SQLException e) {
            System.out.println("반찬 삭제 실패");
            throw new RuntimeException(e);
        }
    }
}
