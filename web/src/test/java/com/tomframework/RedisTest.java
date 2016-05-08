package com.tomframework;

import com.tomframework.config.RootConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Administrator on 2016/5/3 0003.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={RootConfig.class})
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    @Test
    public void runRedis() throws MalformedURLException {
        String userId ="tom";
        URL url = new URL("http://www.bxjr.com");
        listOps.leftPush(userId, url.toExternalForm());
        listOps.leftPush("tom2","hahaha");
    }

}
