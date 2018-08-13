package pers.kakayunmu.bluebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.util.GlobalParam;

import javax.servlet.http.HttpServletRequest;

@Component
public class Authorization {

    @Autowired
    private GlobalParam globalParam;
    @Autowired
    private HttpServletRequest httpServletRequest;

    public String getAuthorization() {
        Object authorization = httpServletRequest.getAttribute("authorization");
        return authorization==null?null:authorization.toString();
    }

    public Member getMember(){
        return globalParam.get(getAuthorization());
    }
}
