package com.tomweb.schedule.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/29 0029
 * Time: 11:34
 */
public class JobListener1 extends JobListenerSupport {
    @Override
    public String getName() {
        return "job1";
    }

    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("to be excuted:"+context.getJobDetail().getKey());
    }

    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("ExecutionVetoed:"+context.getJobDetail().getKey());
    }

    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("WasExecuted:"+context.getJobDetail().getKey());
    }
}
