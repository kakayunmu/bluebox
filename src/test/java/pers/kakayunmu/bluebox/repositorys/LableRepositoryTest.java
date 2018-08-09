package pers.kakayunmu.bluebox.repositorys;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.kakayunmu.bluebox.entity.Goods;
import pers.kakayunmu.bluebox.entity.Lable;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LableRepositoryTest {
    @Autowired
    private LableRepository lableRepository;

    @Before
    public void  init(){
        Lable lable=new Lable();
        lable.setName("鞋子");
        Goods goods=new Goods();
        goods.setName("耐克运动鞋");
        lable.getGoods().add(goods);
        lableRepository.save(lable);
    }
    @Test
    public void findAll(){
        List<Lable> lableList= lableRepository.findAll();

        for(Lable lable :lableList){
            System.out.println(lable.getName());
        }
    }

}
