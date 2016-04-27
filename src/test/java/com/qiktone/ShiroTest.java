package com.qiktone;

import com.qiktone.config.RootConfig;
import com.qiktone.events.event.EmailSendEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/4/27 0027
 * Time: 16:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={RootConfig.class})
public class ShiroTest {

    @Autowired
    private   ApplicationContext applicationContext;


    @Test
    public void test1(){
        System.out.println("==========================" + applicationContext.getBean("securityManager") + "==========================");
        System.out.println("==========================" + applicationContext.getBean("shiroFilterFactoryBean") + "==========================");
        System.out.println("==========================" + applicationContext.getBean("lifecycleBeanPostProcessor") + "==========================");
        System.out.println("=========================="+ applicationContext.getBean("authorizationAttributeSourceAdvisor")+"==========================");
    }

    @Test
    public void testAsynEvent(){
        System.out.println("1111111111");
        applicationContext.publishEvent(new EmailSendEvent("1323"));
        System.out.println("3333333333");
    }

}
