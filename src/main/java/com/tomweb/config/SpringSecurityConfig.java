package com.tomweb.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.event.LoggerListener;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

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

   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

    }*/

    public void configure(WebSecurity web){
        // 设置不拦截规则
        web.ignoring().antMatchers("/css/**","/js/**","/images/**","/img/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
        // 设置拦截规则
        // 自定义accessDecisionManager访问控制器,并开启表达式语言
        http.authorizeRequests()
                .expressionHandler(webSecurityExpressionHandler())
                .antMatchers("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/login")
                .and()
                .formLogin()    //指定登录页是”/login”
                .loginPage("/login")
                .failureUrl("/login?error=1")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()    //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
               // .successHandler(loginSuccessHandler())//code3
                .and()
                .logout()  //退出登录后的默认网址是”/home”
                .logoutSuccessUrl("/")
                .permitAll()
                .invalidateHttpSession(true)
                .and()      //登录后记住用户，下次自动登录// 数据库中必须存在名为persistent_logins的表         //建表语句见code15
                .rememberMe()
                .tokenValiditySeconds(1209600)    //指定记住登录信息所使用的数据源
                .tokenRepository(tokenRepository());//code4;
                //.failureUrl("/login?error=1");

        // 开启默认登录页面
        // http.formLogin();

        // 自定义登录页面
        http.csrf().disable().formLogin().loginPage("/login")
               .permitAll();

        // 自定义注销
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

        // session管理
        http.sessionManagement().sessionFixation().changeSessionId()
                .maximumSessions(1).expiredUrl("/");

        // RemeberMe
        http.rememberMe().key("webmvc#FD637E6D9C0F1A5A67082AF56CE32485");
    }

    // Code4----------------------------------------------
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl j=new JdbcTokenRepositoryImpl();
      /*  j.setDataSource(dataSource1);*/
        return j;
    }    // Code3----------------------------------------------
   /* @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();//code6
    }*/

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
