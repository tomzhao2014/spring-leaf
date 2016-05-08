package com.tomframework.config;

import com.tomframework.security.FormFilter;
import com.tomframework.security.UserRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/4/26 0026
 * Time: 17:20
 */
//aaa@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        String filterDefinitions=" # some example chain definitions:\n" +
                "            /admin/** = authc, roles[admin]\n" +
                "            /docs/** = authc, perms[document:read]\n" +
                "            /** = authc\n" +
                "            # more URL-to-FilterChain definitions here";

        Map<String,Filter> filterMap = new HashMap<String,Filter>();
        filterMap.put("formFilter",formFilter());
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //The 'filters' property is not necessary since any declared javax.servlet.Filter bean
        //defined will be automatically acquired and available via its beanName in chain
        //definitions, but you can perform instance overrides or name aliases here if you like:
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitions(filterDefinitions);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/home");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        // Single realm app.  If you have multiple realms, use the 'realms' property instead.
        webSecurityManager.setRealm(userRealm());
        return webSecurityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }

    /**
     *  Define any javax.servlet.Filter beans you want anywhere in this application context.
        They will automatically be acquired by the 'shiroFilter' bean above and made available
        to the 'filterChainDefinitions' property.  Or you can manually/explicitly add them
        to the shiroFilter's 'filters' Map if desired. See its JavaDoc for more details.
     * @return
     */
    @Bean
    public FormFilter formFilter(){
        return new FormFilter();
    }

    /**
     * Define the Shiro Realm implementation you want to use to connect to your back-end
     * @return
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return  new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
