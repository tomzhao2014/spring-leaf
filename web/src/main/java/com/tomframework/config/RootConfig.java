package com.tomframework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.tomframework.core.orm.mybatis.MybatisSessionFactoryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by tom on 15/12/22.
 */
@Configuration
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages={"com.tomframework.*"}, excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.tomframework.web.*Controller")})
/*@Import({ShiroConfig.class})*/
@Import({CacheConfig.class})
public class RootConfig {

    private Log log = LogFactory.getLog(RootConfig.class);



    @Value("${jdbc.url}")
    protected String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean(initMethod = "init",destroyMethod = "close")
    public DataSource dataSource(){
        log.debug("数据源配置");
        log.debug(url);
        log.debug(username);
       // log.debug(env.getProperty("jdbc.url"));
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/bxwd?userUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
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
        sqlSessionFactory.setPackagesToScan(new String[]{"com.tomframework.entity"});
        Resource resource = new ClassPathResource("/mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(resource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource1 = resolver.getResources("/mapper/*.xml");
        sqlSessionFactory.setMapperLocations(resource1);
        sqlSessionFactory.setDataSource(dataSource());
        return sqlSessionFactory.getObject();
    }

    @Bean
   public MapperScannerConfigurer mybatisMapperScanner(){
       MapperScannerConfigurer mybatisMapperScanner = new MapperScannerConfigurer();
        mybatisMapperScanner.setBasePackage("com.tomframework.repository");
        return mybatisMapperScanner;
   }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
        return dataSourceTransactionManager;
    }


    /**
     *
     * PropertySourcesPlaceHolderConfigurer Bean only required for @Value("{}") annotations.
     * Remove this bean if you are not using @Value annotations for injecting properties.
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("application-develop.properties"));
        return propertySourcesPlaceholderConfigurer;
    }


}
