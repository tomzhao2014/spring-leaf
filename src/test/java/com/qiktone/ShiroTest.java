package com.qiktone;

import com.qiktone.config.RootConfig;
import com.qiktone.entity.Company;
import com.qiktone.events.event.EmailSendEvent;
import com.qiktone.service.DemoService;
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
        System.out.println("==========================" + applicationContext+ "==========================");


    }

    @Test
    public void testAsynEvent(){
        System.out.println("1111111111");
        applicationContext.publishEvent(new EmailSendEvent("1323"));
        System.out.println("3333333333");
    }

    @Test
    public void testCache(){
        DemoService demoService = (DemoService)applicationContext.getBean("demoService");
        Company company  = new Company();
        demoService.findAccount0(company,true,true);
        demoService.findAccount1(company,true,true);
        demoService.findAccount2(company,true,true);
        demoService.findAccount3(company,true,true);

    }

}
