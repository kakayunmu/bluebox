package pers.kakayunmu.bluebox.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.repositorys.LableRepository;
import pers.kakayunmu.bluebox.repositorys.MemberRepository;
import pers.kakayunmu.bluebox.util.GlobalParam;

import javax.servlet.http.HttpServletRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LableTest {
    @Autowired
    private LableRepository lableRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private GlobalParam globalParam;

    @Before
    public void before(){
        httpServletRequest.setAttribute("authorization","testAuthorization");
        Member one = memberRepository.findOne(1l);
        globalParam.push("testAuthorization",one);
    }

    @Test
    public void LableInitTest(){
        LableController.InitLable(1,lableRepository);
    }
}
