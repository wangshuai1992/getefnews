package com.wangshuai.efnews.common.config;

import com.wangshuai.sso.share.service.AuthenticationRpcService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-07 22:09
 */
//@Configuration
public class HessianConfig {

    @Bean
    public static HessianProxyFactoryBean getHessianProxyFactoryBean(Environment environment) {
        String ssoServer = environment.getProperty("sso.server");
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setServiceUrl(ssoServer + "/authenticationRpcService");
        hessianProxyFactoryBean.setServiceInterface(AuthenticationRpcService.class);
        return hessianProxyFactoryBean;
    }

}
