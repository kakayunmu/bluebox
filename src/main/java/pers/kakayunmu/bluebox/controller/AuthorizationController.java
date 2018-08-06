package pers.kakayunmu.bluebox.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.model.common.RetDataModel;
import pers.kakayunmu.bluebox.model.common.RetModel;
import pers.kakayunmu.bluebox.repositorys.MemberRepository;
import pers.kakayunmu.bluebox.service.WeChatService;
import pers.kakayunmu.bluebox.util.GlobalParam;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class AuthorizationController {

    @Value("${wechat.appid}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${spring.profiles.active}")
    private String active;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private WeChatService weChatService;
    @Autowired
    private GlobalParam globalParam;

    @RequestMapping(value = "/login/{jscode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object login(@PathVariable(value = "jscode") String jscode) throws Exception {

        if(active.indexOf("dev")!=-1){
            return testLogin();
        }

        Object obj = weChatService.getOpenId(appId, secret, jscode, "authorization_code");
        log.info("getOpenId===========>{}", obj.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap hashMap = objectMapper.readValue(obj.toString(), HashMap.class);

        if (hashMap.containsKey("errcode")) {
            return new RetModel(-1, hashMap.get("errmsg").toString());
        } else {
            Member member = memberRepository.findByOpenid(hashMap.get("openid").toString());
            if (member == null) {
                member = new Member();
            }
            member.setOpenid(hashMap.get("openid").toString());
            member.setSession_key(hashMap.get("session_key").toString());
            memberRepository.save(member);
            String uuid= UUID.randomUUID().toString();
            globalParam.push(uuid,member);
            return new RetDataModel(0, "登录成功",uuid);
        }
    }

    private RetDataModel testLogin(){
        String testOpenId="testOpenId";
        String testSession_key="testSession_key";
        Member member=memberRepository.findByOpenid(testOpenId);
        if(member==null){
            member=new Member();
        }
        member.setOpenid(testOpenId);
        member.setSession_key(testSession_key);
        memberRepository.save(member);
        String uuid=UUID.randomUUID().toString();
        globalParam.push(uuid,member);
        return  new RetDataModel(0,"模拟登录成功",uuid);
    }
}
