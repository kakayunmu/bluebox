package pers.kakayunmu.bluebox.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://api.weixin.qq.com",name = "wechat")
public interface WeChatService {

    @RequestMapping(value = "/sns/jscode2session?appid={appid}&secret={secret}&js_code={jscode}&grant_type={grenttype}",method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    String getOpenId(@PathVariable(value = "appid")String appId,
                       @PathVariable(value = "secret")String secret,
                       @PathVariable(value = "jscode")String jscode,
                       @PathVariable(value = "grenttype")String grenttype);
}
