package com.tomweb.schedule.quartz.jobs;

import org.quartz.*;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/26 0026
 * Time: 10:20
 */
public class HelloJob implements Job {
     String name;
     int age;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello quartz");

        JobDetail jobDetail =context.getJobDetail();

        JobKey jobKey=jobDetail.getKey();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        String name = jobDataMap.getString("name");

        int age = jobDataMap.getInt("age");

        System.out.println("jobkey:"+jobKey+",name="+name+",age="+age);

        //使用context.getMergedJobDataMap()获取数据
        JobDataMap jobDataMap1 = context.getMergedJobDataMap();
        String name1 = jobDataMap1.getString("name");
        int age1 = jobDataMap1.getInt("age");
        System.out.println("jobkey:"+jobKey+",nam1e="+name1+",age1="+age1);

        //使用factory注入获取数据
        System.out.println("jobkey:"+jobKey+",name3="+this.name+",3="+this.age);

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
