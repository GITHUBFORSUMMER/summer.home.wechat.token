package com.huangyingsheng.wechat.token.job;

import com.huangyingsheng.wechat.token.job.jobs.TokenJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class App {
    public static void main(String[] args) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            JobDetail jobDetail = JobBuilder.newJob(TokenJob.class).withIdentity("job1", "name1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group2")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(100).repeatForever()).build();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
