package pers.kakayunmu.bluebox.repositorys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import pers.kakayunmu.bluebox.entity.vo.LableVO;
import pers.kakayunmu.bluebox.util.JacksonUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LableRepositoryTest {
    @Autowired
    private LableRepository lableRepository;

    @Test
    public void findLablesByCreateByWithGoodCountTest() {
        Pageable pageable=new PageRequest(0,10);
        Page<LableVO> ret = lableRepository.findLablesByCreateByWithGoodCount(pageable,"a278d751-a1be-11e8-8e67-54ee75a04edd");
            System.out.println(JacksonUtil.toJson(ret));
    }
}
