package com.qiktone.config;

import com.qiktone.security.FormFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/4/26 0026
 * Time: 17:20
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        String filterDefinitions=" # some example chain definitions:\n" +
                "            /admin/** = authc, roles[admin]\n" +
                "            /docs/** = authc, perms[document:read]\n" +
                "            /** = authc\n" +
                "            # more URL-to-FilterChain definitions here";

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
      // shiroFilterFactoryBean.setFilterChainDefinitionMap();
        shiroFilterFactoryBean.setFilterChainDefinitions(filterDefinitions);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/home");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
      //  shiroFilterFactoryBean.setSecurityManager();
        return shiroFilterFactoryBean;
    }



}
