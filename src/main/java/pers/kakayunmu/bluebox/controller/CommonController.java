package pers.kakayunmu.bluebox.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.model.common.RetModel;
import pers.kakayunmu.bluebox.model.wechat.UserInfo;
import pers.kakayunmu.bluebox.repositorys.MemberRepository;
import pers.kakayunmu.bluebox.util.JacksonUtil;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class CommonController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private Authorization authorization;

    @RequestMapping(value = "/setUserInfo",method = RequestMethod.POST)
    public Object setUserInfo(@RequestBody UserInfo userInfo){
        log.info("setUserInfo 参数============>{}",JacksonUtil.toJson(userInfo));
        Member member= authorization.getMember();
        member.setName(userInfo.getNick_name());
        member.setGender(Integer.valueOf(userInfo.getGender()));
        member.setCountry(userInfo.getCountry());
        member.setProvince(userInfo.getProvince());
        member.setCity(userInfo.getCity());
        member.setHeadImgUrl(userInfo.getAvatar_url());
        memberRepository.save(member);
        return new RetModel(0,"保存数据成功");
    }
}
