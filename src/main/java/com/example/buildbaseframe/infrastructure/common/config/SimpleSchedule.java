package com.example.buildbaseframe.infrastructure.common.config;

import com.example.buildbaseframe.utils.schedule.SimpleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/6/20 20:29
 * @version: 1.0
 */
//@Configuration
public class SimpleSchedule {
    @Bean
    public JobDetail simpleJobDetail(){
        return JobBuilder.newJob(SimpleJob.class)
                .withIdentity("simplejob")  // 用于标识区分不同的job
                .usingJobData("name","test")    // 动态传入Job任务中的参数
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger simpleJobTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)  // 每十秒触发
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(simpleJobDetail())
                .withIdentity("simpleTrigger")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }
}
