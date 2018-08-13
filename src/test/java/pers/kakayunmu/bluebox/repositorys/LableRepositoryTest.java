package pers.kakayunmu.bluebox.repositorys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.kakayunmu.bluebox.entity.Lable;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LableRepositoryTest {
    @Autowired
    private LableRepository lableRepository;

    @Test
    public void findAll(){
        List<Lable> lableList= lableRepository.findAll();

        for(Lable lable :lableList){
            System.out.println(lable.getName());
        }
    }

}
