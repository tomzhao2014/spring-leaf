package com.qiktone.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.qiktone.core.orm.mybatis.MybatisSessionFactoryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by tom on 15/12/22.
 */
@Configuration
@ComponentScan(basePackages={"com.qiktone.*"}, excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.qiktone.web.*Controller")})
//@PropertySource("classpath:/application-develop.properties")

@EnableTransactionManagement
//@ImportResource("classpath:system-config.xml")
@Import({ShiroConfig.class})
public class RootConfig {

    private Log log = LogFactory.getLog(RootConfig.class);

   /* @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;*/

    @Bean(initMethod = "init",destroyMethod = "close")
    public DataSource dataSource(){
        log.debug("数据源配置");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/qiktone?userUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
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
        sqlSessionFactory.setPackagesToScan(new String[]{"com.qiktone.entity"});
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
        mybatisMapperScanner.setBasePackage("com.qiktone.repository");
        return mybatisMapperScanner;
   }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
        return dataSourceTransactionManager;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
