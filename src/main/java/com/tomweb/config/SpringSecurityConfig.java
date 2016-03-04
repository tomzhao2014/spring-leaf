package com.tomweb.config;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.event.LoggerListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

/**
 * 系统安全配置
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2015/12/25 0025
 * Time: 13:54
 * http://wenku.baidu.com/link?url=HHVE25dyuT3O0q283PbkjidPIptlfprojZRQ05IPQRaqHLE87krRG94MJbXtS5ygmS4IGhWTfkEJhyGcTeyyyNiN9B4VnIG-jlcWFxs9ia_
 */
@Configuration
@EnableWebSecurity

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private Log log = LogFactory.getLog(SpringSecurityConfig.class);

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("tomzhao").password("123456").roles("USER");
    }

    public void configure(WebSecurity web){
        // 设置不拦截规则
        web.ignoring().antMatchers("/css/**","/js/**","/images/**","/img/**","/fonts/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .permitAll();

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
              /*  .logoutSuccessHandler(logoutSuccessHandler)
                .invalidateHttpSession(true)
                .addLogoutHandler(logoutHandler)
                .deleteCookies(cookieNamesToClear)*/;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }


    @Bean
    public LoggerListener loggerListener() {
        log.info("org.springframework.security.authentication.event.LoggerListener");
        LoggerListener loggerListener = new LoggerListener();

        return loggerListener;
    }

    @Bean
    public org.springframework.security.access.event.LoggerListener eventLoggerListener() {
        log.info("org.springframework.security.access.event.LoggerListener");
        org.springframework.security.access.event.LoggerListener eventLoggerListener = new org.springframework.security.access.event.LoggerListener();

        return eventLoggerListener;
    }




    /*
    * 表达式控制器
    */
    @Bean(name = "expressionHandler")
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        log.info("DefaultWebSecurityExpressionHandler");
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        return webSecurityExpressionHandler;
    }

    /*
     * 表达式投票器
     */
    @Bean(name = "expressionVoter")
    public WebExpressionVoter webExpressionVoter() {
        log.info("WebExpressionVoter");
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
        return webExpressionVoter;
    }

}
