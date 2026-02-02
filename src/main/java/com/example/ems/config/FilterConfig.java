package com.example.ems.config;

import com.example.ems.filters.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomFilter> authenticationFilterRegistration() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CustomFilter());

        registrationBean.addUrlPatterns("/api/*");

        registrationBean.setOrder(1);

        return registrationBean;
    }
}
