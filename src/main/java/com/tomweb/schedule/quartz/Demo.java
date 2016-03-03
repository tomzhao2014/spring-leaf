package com.tomweb.schedule.quartz;

import com.tomweb.schedule.quartz.jobs.HelloJob;
import com.tomweb.schedule.quartz.listener.JobListener1;
import com.tomweb.schedule.quartz.listener.ScheduleListener;
import org.quartz.*;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/26 0026
 * Time: 10:19
 */
public class Demo {
    public static void main(String[] args) throws SchedulerException {

        HolidayCalendar holidayCalendar = new HolidayCalendar();
        holidayCalendar.addExcludedDate(new Date());

        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFact.getScheduler();

        //scheduler.addCalendar("myCalendar",holidayCalendar,false,true);

        scheduler.getListenerManager().addJobListener(new JobListener1(), GroupMatcher.jobGroupEquals("group"));
        scheduler.getListenerManager().addSchedulerListener(new ScheduleListener());

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .usingJobData("name","tom")
                .usingJobData("age",18)
                .withIdentity("job1", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
              //  .modifiedByCalendar("myCalendar")
                .build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        scheduler.shutdown();
    }
}
