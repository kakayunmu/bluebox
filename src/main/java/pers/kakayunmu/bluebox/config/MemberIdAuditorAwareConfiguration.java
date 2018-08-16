package pers.kakayunmu.bluebox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import pers.kakayunmu.bluebox.controller.Authorization;
import pers.kakayunmu.bluebox.entity.Member;

/**
 * JPA BaseEntity 获取当前用户配置
 */
@Configuration
public class MemberIdAuditorAwareConfiguration implements AuditorAware<String> {

    @Autowired
    private Authorization authorization;

    @Override
    public String getCurrentAuditor() {
        Member member = authorization.getMember();
        if (member == null)
            return "00000000-0000-0000-0000-000000000000";
        else
            return member.getId();
    }
}
