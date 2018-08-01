package pers.kakayunmu.bluebox.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.kakayunmu.bluebox.filter.AuthorizationFilter;

import javax.servlet.Filter;

@Configuration
public class AuthorizationFilterConfiguration {
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authorizationFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("authorizationFilter");
        return registration;
    }

    @Bean
    public Filter authorizationFilter() {
        return new AuthorizationFilter();
    }
}
