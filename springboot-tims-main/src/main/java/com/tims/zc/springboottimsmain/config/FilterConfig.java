package com.tims.zc.springboottimsmain.config;

import com.tims.zc.filter.A_LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<A_LoginCheckFilter> aLoginCheckFilter() {
        FilterRegistrationBean<A_LoginCheckFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new A_LoginCheckFilter());
        filterRegistrationBean.addUrlPatterns("/hello");
        return filterRegistrationBean;
    }
}
