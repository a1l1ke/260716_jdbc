package org.example.step6;

import org.example.entity.Banchan;
import org.example.repository.BanchanRepository;

public class MyCRUD {
    public static void main(String[] args) {
        BanchanRepository repository = new BanchanRepository();
        System.out.println("repository.findAll() = " + repository.findAll());
        repository.save(new Banchan(0, "열무김치"));
        // 제약조건을 어김 (255글자까지인데 400글자 넣기 시도)
//        repository.save(new Banchan(0, "열무김치".repeat(100)));
        System.out.println("repository.findAll() = " + repository.findAll());
    }
}
