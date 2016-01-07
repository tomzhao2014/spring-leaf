package com.tomweb.config;


import com.tomweb.message.jms.JmsMessageConsumer;
import com.tomweb.message.jms.JmsMessageService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2015/12/29 0029
 * Time: 9:31
 */
@Configuration
@PropertySource("classpath:/application-develop.properties")
public class JmsConfig {

    @Autowired
    Environment environment;

    @Bean
    public ActiveMQConnectionFactory ampConnectionFactory(){
        ActiveMQConnectionFactory ampConnectionFactory = new ActiveMQConnectionFactory();
        ampConnectionFactory.setBrokerURL(environment.getProperty("jms.uri"));
        return  ampConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(ampConnectionFactory());
        connectionFactory.setSessionCacheSize(100);
        return connectionFactory;
    }

    @Bean
    public ActiveMQQueue destination(){
        ActiveMQQueue queue = new ActiveMQQueue(environment.getProperty("jms.destination"));
        return queue;
    }

    @Bean
    public SimpleMessageConverter messageConverter(){
        return  new SimpleMessageConverter();
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setMessageConverter(messageConverter());
        return jmsTemplate;
    }

    @Bean
    public JmsMessageService messageService(){
        JmsMessageService messageService = new JmsMessageService();
        messageService.setJmsTemplate(jmsTemplate());
        messageService.setDefaultDestination(destination());
        return messageService;
    }

    @Bean
    public JmsMessageConsumer messageConsumer(){
        return new JmsMessageConsumer();
    }

    @Bean
    public MessageListenerAdapter messageListener(){
        MessageListenerAdapter messageListener = new MessageListenerAdapter();
        messageListener.setMessageConverter(messageConverter());
        messageListener.setDelegate(messageConsumer());
        return  messageListener;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(5000);
        threadPoolTaskExecutor.setThreadNamePrefix("jmsTaskExecutor-");
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        return  threadPoolTaskExecutor;

    }




}
