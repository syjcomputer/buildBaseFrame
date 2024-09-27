package com.example.buildbaseframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching  // 开启缓存
//@EnableScheduling
public class BuildBaseFrameApplication {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        SpringApplication.run(BuildBaseFrameApplication.class, args);
    }

}
