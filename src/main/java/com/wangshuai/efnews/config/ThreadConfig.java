package com.wangshuai.efnews.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-30 01:51
 */
@Configuration
@EnableAsync
public class ThreadConfig implements AsyncConfigurer {

    @Value("${efnews.executor.corepoolsize}")
    private int corePoolSize;

    @Value("${efnews.executor.maxpoolsize}")
    private int maxPoolSize;

    @Value("${efnews.executor.queuecapacity}")
    private int queueCapacity;

    /**
     * The {@link Executor} instance to be used when processing async
     * method invocations.
     */
    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }

    /**
     * The {@link AsyncUncaughtExceptionHandler} instance to be used
     * when an exception is thrown during an asynchronous method execution
     * with {@code void} return type.
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
