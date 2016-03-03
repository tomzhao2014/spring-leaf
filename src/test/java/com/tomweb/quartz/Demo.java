package com.tomweb.quartz;


import com.tomweb.schedule.quartz.jobs.HelloJob;
import org.junit.Test;
import org.quartz.*;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/26 0026
 * Time: 10:22
 */
public class Demo {

    @Test
    public void hello() throws SchedulerException {

        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFact.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                                        .withIdentity("job1", "group1")
                                        .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withIdentity("trigger1", "group1")
                                        .startNow()
                                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                                .withIntervalInSeconds(1)
                                                .repeatForever())
                                        .build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();

    }
}
