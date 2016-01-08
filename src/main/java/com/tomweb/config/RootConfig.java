package com.tomweb.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.tomweb.core.SpringContext;
import com.tomweb.core.orm.mybatis.MybatisSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by tom on 15/12/22.
 */
@Configuration
@ComponentScan(basePackages={"com.tomweb"}, excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class)})
@PropertySource("classpath:/application-develop.properties")
@Import({JmsConfig.class})
@EnableTransactionManagement
@ImportResource("classpath:system-config.xml")
public class RootConfig {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean(initMethod = "init",destroyMethod = "close")
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        System.out.println("======1111========="+url);
        System.out.println("===============" + username);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
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

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSessionFactoryBean sqlSessionFactory = new MybatisSessionFactoryBean();
        sqlSessionFactory.setPackagesToScan(new String[]{"com.tomweb.entity"});
        Resource resource = new ClassPathResource("classpath:mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(resource);
        Resource resource1 = new ClassPathResource("classpath*:/mapper/*.xml");
        sqlSessionFactory.setMapperLocations(new Resource[]{resource1});
        sqlSessionFactory.setDataSource(dataSource());

        return sqlSessionFactory.getObject();
    }

    @Bean
   public MapperScannerConfigurer mybatisMapperScanner(){
       MapperScannerConfigurer mybatisMapperScanner = new MapperScannerConfigurer();
        mybatisMapperScanner.setBasePackage("com.tomweb.repository");
        return mybatisMapperScanner;
   }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
        return dataSourceTransactionManager;
    }


}
