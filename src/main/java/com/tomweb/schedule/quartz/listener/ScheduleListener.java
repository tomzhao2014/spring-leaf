package com.tomweb.schedule.quartz.listener;

import org.quartz.*;
import org.quartz.listeners.SchedulerListenerSupport;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/2/29 0029
 * Time: 13:17
 */
public class ScheduleListener extends SchedulerListenerSupport{
    public void jobAdded(JobDetail jobDetail) {
        System.out.println("add job:"+jobDetail.getKey());
    }

    public void jobDeleted(JobKey jobKey) {
        System.out.println();
    }

    public void jobPaused(JobKey jobKey) {
        System.out.println();
    }

    public void jobResumed(JobKey jobKey) {
        System.out.println();
    }

    public void jobScheduled(Trigger trigger) {
        System.out.println();
    }

    public void jobsPaused(String jobGroup) {
        System.out.println();
    }

    public void jobsResumed(String jobGroup) {
        System.out.println();
    }

    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println();
    }

    public void schedulerError(String msg, SchedulerException cause) {
        System.out.println();
    }

    public void schedulerInStandbyMode() {
        System.out.println();
    }

    public void schedulerShutdown() {
        System.out.println("shutdown schedule");
    }

    public void schedulerShuttingdown() {
        System.out.println();
    }

    public void schedulerStarted() {
        System.out.println();
    }

    public void schedulerStarting() {
        System.out.println();
    }

    public void triggerFinalized(Trigger trigger) {
        System.out.println();
    }

    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println();
    }

    public void triggerResumed(TriggerKey triggerKey) {
        System.out.println();
    }

    public void triggersPaused(String triggerGroup) {
        System.out.println();
    }

    public void triggersResumed(String triggerGroup) {
        System.out.println();
    }

    public void schedulingDataCleared() {
        System.out.println();
    }
}
