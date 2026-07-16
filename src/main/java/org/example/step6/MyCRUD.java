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

        // 개별 읽기 -> 수정 -> 삭제
        Banchan myBanchan = repository.findById(1);
        System.out.println("myBanchan = " + myBanchan);
        Banchan myBanchan2 = new Banchan(
                myBanchan.id(),
                myBanchan.name() + "라는 이름..."
        );
        repository.update(myBanchan2);
        System.out.println("repository.findAll() = " + repository.findAll());
        repository.deleteById(1);
        System.out.println("repository.findAll() = " + repository.findAll());
    }
}
