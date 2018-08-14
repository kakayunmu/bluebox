package pers.kakayunmu.bluebox.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.model.LableModel;
import pers.kakayunmu.bluebox.repositorys.LableRepository;
import pers.kakayunmu.bluebox.repositorys.MemberRepository;
import pers.kakayunmu.bluebox.util.GlobalParam;
import pers.kakayunmu.bluebox.util.JacksonUtil;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void before() {
        httpServletRequest.setAttribute("authorization", "testAuthorization");
        Member one = memberRepository.findOne(1l);
        globalParam.push("testAuthorization", one);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void lableInitTest() {
        LableController.InitLable(1, lableRepository);
    }

    @Test
    public void findByMemberId() throws Exception {
        ;
        String responseString = mockMvc.perform
                (
                        get("/api/lable/getByMember/1")          //请求的url,请求的方法是get
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)//数据的格式
                )
                .andExpect(status().isOk())    //返回的状态是200
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void saveTest() throws Exception {
        LableModel lableModel=new LableModel();
        lableModel.setName("标签1");
        lableModel.setIsSys(1);
        lableModel.setRemark("这里是备注");
        String body=JacksonUtil.toJson(lableModel);
        System.out.println(body);
        String responseString=mockMvc.perform(
                post("/api/lable/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }
}
