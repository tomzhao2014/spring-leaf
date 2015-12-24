package com.tomweb.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by tom on 15/12/22.
 */
@Configuration
@ComponentScan(basePackages={"com.tomweb"}, excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class)})
@PropertySource("classpath:/application-develop.properties")
public class RootConfig {

    @Autowired
    Environment environment;

    @Bean(initMethod = "init",destroyMethod = "close")
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(19);
        dataSource.setMaxWait(2);
        dataSource.setTimeBetweenEvictionRunsMillis(6000);
        dataSource.setMinEvictableIdleTimeMillis(30000);
        dataSource.setValidationQuery("select 'x' from dual");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }


}
