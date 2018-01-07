package com.wangshuai.efnews.common.config;

import com.wangshuai.sso.client.filter.LogoutFilter;
import com.wangshuai.sso.client.filter.SsoFilter;
import com.wangshuai.sso.share.service.AuthenticationRpcService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-07 22:15
 */
@Configuration
public class FilterConfig {

    @Resource
    private Environment environment;

    @Resource
    private AuthenticationRpcService authenticationRpcService;

    @Bean
    public FilterRegistrationBean getSsoFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        SsoFilter ssoFilter = new SsoFilter();
        ssoFilter.setEnvironment(environment);
        ssoFilter.setAuthenticationRpcService(authenticationRpcService);

        registration.setFilter(ssoFilter);
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("ssoFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean getLogoutFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setEnvironment(environment);
        logoutFilter.setAuthenticationRpcService(authenticationRpcService);

        registration.setFilter(logoutFilter);
        registration.addUrlPatterns("/logout");
        registration.setName("logoutFilter");
        registration.setOrder(2);
        return registration;
    }
}
