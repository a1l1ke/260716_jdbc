package org.example.repository;

import org.example.entity.Banchan;

import java.util.List;

public class BanchanRepository {
    // save (create)
    public void save(Banchan banchan) {
        System.out.println("BanchanRepository.save");
    }

    // find / findAll (read)
    public Banchan findById(long id) {
        System.out.println("BanchanRepository.findById");
        return null;
    }

    public List<Banchan> findAll() {
        System.out.println("BanchanRepository.findAll");
        return null;
    }

    // update - persistence
    // 1. save -> save upsert?
    // 2. update -> 별도로 update를 하지 않아도 entity가 변경되면 알아서 업데이트 (JPA)
    public void update(Banchan banchan) {
        System.out.println("BanchanRepository.update");
        // 전체 새로운 데이터를 넣어서 처리
        // 원본을 복제해서 -> 수정할 것만 바꾸고 -> 새롭게 넣어서
    }

    public void deleteById(long id) {
        System.out.println("BanchanRepository.deleteById");
    }
}
