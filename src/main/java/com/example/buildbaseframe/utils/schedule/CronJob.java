package com.example.buildbaseframe.utils.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/6/20 20:44
 * @version: 1.0
 */
public class CronJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(SimpleJob.class);

    private String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Hello！ "+this.name);

    }
}