package pers.kakayunmu.bluebox.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pers.kakayunmu.bluebox.entity.Member;
import pers.kakayunmu.bluebox.repositorys.MemberRepository;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "pers.kakayunmu.**.repositorys")
@EntityScan(basePackages = "pers.kakayunmu.**.entity")
@Slf4j
public class JpaConfiguration {
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return  new PersistenceExceptionTranslationPostProcessor();
    }
    @Bean
    public CommandLineRunner MemberInit(MemberRepository repository) {
        return (args) -> {
            long count = repository.count();
            if(count==0l){
                log.info("用户表初始化，创建管理员");
                Member member=new Member();
                member.setId("00000000-0000-0000-0000-000000000000");
                member.setName("系统");
                member.setOpenid("SYSTEM");
                repository.save(member);
            }
        };
    }
}
