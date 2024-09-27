package com.example.buildbaseframe.utils.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @description:
 * @author: sunyujie
 * @time: 2023/6/20 18:49
 * @version: 1.0
 */

@Component
public class SchedulerTask {

    private static final Logger loggger = LoggerFactory.getLogger(SchedulerTask.class);

    @Scheduled(cron = "*/10 * * * * ?")
    private void taskCron(){
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

        loggger.info("现在时间 schedule: " + dataFormat.format(new Date()));
    }
}
