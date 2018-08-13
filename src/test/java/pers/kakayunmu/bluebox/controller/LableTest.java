package pers.kakayunmu.bluebox.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.kakayunmu.bluebox.repositorys.LableRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LableTest {
    @Autowired
    private LableRepository lableRepository;

    @Test
    public void LableInitTest(){
        LableController.InitLable(1,lableRepository);
    }
}
