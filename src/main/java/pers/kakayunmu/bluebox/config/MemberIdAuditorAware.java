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
public class MemberIdAuditorAware implements AuditorAware<Long> {

    @Autowired
    private Authorization authorization;

    @Override
    public Long getCurrentAuditor() {
        Member member = authorization.getMember();
        if (member == null)
            return 0l;
        else
            return member.getId();
    }
}
