package com.biblioteca.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);    // threads sempre ativas
        executor.setMaxPoolSize(10);   // maximo de threads simultaneas
        executor.setQueueCapacity(100); // fila de espera
        executor.setThreadNamePrefix("biblioteca-async-");
        executor.initialize();
        return executor;
    }
}