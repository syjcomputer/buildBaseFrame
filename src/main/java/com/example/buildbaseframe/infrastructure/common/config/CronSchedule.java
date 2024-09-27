package com.example.buildbaseframe.infrastructure.common.config;

import com.example.buildbaseframe.utils.schedule.CronJob;
import com.example.buildbaseframe.utils.schedule.SimpleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/6/20 20:45
 * @version: 1.0
 */
@Configuration
public class CronSchedule {
    @Bean
    public JobDetail cronJobDetail(){
        return JobBuilder.newJob(CronJob.class)
                .withIdentity("cronjob")  // 用于标识区分不同的job
                .usingJobData("name","cronTest")    // 动态传入Job任务中的参数
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger simpleJobTrigger(){
        CronScheduleBuilder scheduleBuilder= CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

        return TriggerBuilder.newTrigger()
                .forJob(cronJobDetail())
                .withIdentity("simpleTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
