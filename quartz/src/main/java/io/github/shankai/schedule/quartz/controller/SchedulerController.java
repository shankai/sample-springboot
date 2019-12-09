package io.github.shankai.schedule.quartz.controller;

import java.util.HashSet;
import java.util.Set;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.shankai.schedule.quartz.job.MyJob;

/**
 * SchedulerController
 */
@RestController
public class SchedulerController {

    @Autowired
    private Scheduler scheduler;

    @PostMapping("/jobs")
    public void addJob() throws SchedulerException {
        this.scheduleJob(scheduler);
    }

    private void scheduleJob(final Scheduler scheduler) throws SchedulerException {

        JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();

        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule();

        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
                .withSchedule(ssb.withIntervalInSeconds(1).repeatForever()).build();

        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startNow()
                .withSchedule(ssb.withIntervalInSeconds(3).withRepeatCount(20)).build();

        Set<Trigger> triggers = new HashSet<>();
        triggers.add(trigger1);
        triggers.add(trigger2);

        scheduler.scheduleJob(job, triggers, true);
    }

}