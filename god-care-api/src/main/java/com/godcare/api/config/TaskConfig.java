package com.godcare.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskConfig {
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors()); // 최소 스레드 수
//        taskExecutor.setCorePoolSize(30);
        taskExecutor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());  // 최대 스레드 수
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("Executor-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
