package pers.kakayunmu.bluebox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.util.GlobalParam;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class Authorization {

    @Autowired
    private GlobalParam globalParam;
    @Autowired
    private HttpServletRequest httpServletRequest;

    public String getAuthorization() {
        try {
            Object authorization = httpServletRequest.getAttribute("authorization");
            return authorization == null ? null : authorization.toString();
        }catch (Exception ex){
            log.info("从请求上下文未能获取到 Authorization");
            return  null;
        }
    }

    public Member getMember() {
        return globalParam.get(getAuthorization());
    }
}
