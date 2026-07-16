package org.example.step6;

import org.example.entity.Banchan;
import org.example.repository.BanchanRepository;

import java.util.List;
import java.util.Scanner;

public class MyCRUD {
    public static void main(String[] args) {
        BanchanRepository repository = new BanchanRepository();
        System.out.println("repository.findAll() = " + repository.findAll());
        Scanner scanner = new Scanner(System.in);
        System.out.println("메뉴이름을 입력해주세요!");
        String name = scanner.nextLine();
//        repository.save(new Banchan(0, "열무김치"));
        repository.save(new Banchan(0, name));
        // 제약조건을 어김 (255글자까지인데 400글자 넣기 시도)
//        repository.save(new Banchan(0, "열무김치".repeat(100)));
        System.out.println("repository.findAll() = " + repository.findAll());

        // 개별 읽기 -> 수정 -> 삭제
        List<Banchan> banchanList = repository.findAll();
        System.out.println("banchanList = " + banchanList);
        Banchan myBanchan = repository.findById(banchanList.get(0).id());
        System.out.println("myBanchan = " + myBanchan);
        Banchan myBanchan2 = new Banchan(
                myBanchan.id(),
                myBanchan.name() + "라는 이름..."
        );
        repository.update(myBanchan2);
        // 가장 오래된 데이터를 삭제
        repository.deleteById(banchanList.get(0).id());
        System.out.println("repository.findAll() = " + repository.findAll());
    }
}
