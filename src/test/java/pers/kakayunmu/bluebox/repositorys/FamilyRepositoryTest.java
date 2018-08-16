package pers.kakayunmu.bluebox.repositorys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.kakayunmu.bluebox.entity.Family;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyRepositoryTest {
    @Autowired
    private FamilyRepository familyRepository;

    @Test
    public void save(){
        Family family=new Family();
        family.setName("测试family");
        Family ret= familyRepository.save(family);
        System.out.println(ret.getId());
    }
}
