package com.bbva.TpIntegrador.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class AsyncConfig {

    @Value("${base}")
    private int base;
    @Bean(name = "taskExecutor")
    public Executor asyncExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Número mínimo de hilos en el pool
        executor.setMaxPoolSize(base); // Número máximo de hilos en el pool
        executor.setQueueCapacity(25); // Capacidad de la cola para tareas pendientes
        executor.setThreadNamePrefix("ProductService-Async-");
        executor.initialize();
        return executor;
    }
}