package com.tomweb.config;

import com.tomweb.schedule.quartz.jobs.HelloJob;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.text.ParseException;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/29 0029
 * Time: 14:20
 */
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setName("job1");
        jobDetailFactoryBean.setGroup("group1");
        jobDetailFactoryBean.setJobClass(HelloJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "class");
        jobDataMap.put("age",17);
        jobDetailFactoryBean.setJobDataMap(jobDataMap);
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean.getObject();
    }


    @Bean
   public SimpleTrigger simpleTrigger(){
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setRepeatInterval(65000);
        simpleTriggerFactoryBean.setStartDelay(1000);
        simpleTriggerFactoryBean.setName("simpletrigger");
        simpleTriggerFactoryBean.setGroup("group1");
        simpleTriggerFactoryBean.setJobDetail(jobDetail());
        simpleTriggerFactoryBean.afterPropertiesSet();
        return simpleTriggerFactoryBean.getObject();
   }


    @Bean
    public CronTrigger cronTrigger() throws ParseException {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail());
        cronTriggerFactoryBean.setName("crontrigger");
        cronTriggerFactoryBean.setCronExpression("0 0 6 * * ?");
        cronTriggerFactoryBean.afterPropertiesSet();
        return cronTriggerFactoryBean.getObject();
    }





    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.afterPropertiesSet();
        schedulerFactoryBean.setTriggers(simpleTrigger(),cronTrigger());
        return  schedulerFactoryBean;
    }
}
