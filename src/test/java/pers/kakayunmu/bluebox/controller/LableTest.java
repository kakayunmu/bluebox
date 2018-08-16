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
    private WebApplicationContext wac;
    @Autowired
    private GlobalParam globalParam;
    @Autowired
    private HttpServletRequest httpServletRequest;

    private MockMvc mockMvc;
    private Member member;// 系统管理员

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        member=memberRepository.findByOpenid("SYSTEM");
    }

    @Test
    public void lableInitTest() {
        LableController.InitLable(member.getId(), lableRepository);
        //httpServletRequest.setAttribute("authorization","testAuthorization");

    }

    @Test
    public void findByMemberId() throws Exception {
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
        lableModel.setId("f4c15f60-a104-11e8-8050-00e04c3602ed");
        lableModel.setName("标签2");
        lableModel.setIsSys(1);
        lableModel.setRemark("这里是备注");
        String body=JacksonUtil.toJson(lableModel);
        System.out.println(body);
        globalParam.push("testAuthorization",member);

        String responseString=mockMvc.perform(
                post("/api/lable/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body)
                .requestAttr("authorization","testAuthorization")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }
}
