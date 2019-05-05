package com.jarvis.springboot.util.async;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceThreadPoolTaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author: jarvis.fu
 * <p>
 * async config
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer, AsyncUncaughtExceptionHandler {

    @Value("${aot.aoc.api.executor.corePoolSize:4}")
    private int corePoolSize;
    @Value("${aot.aoc.api.executor.maxPoolSize:8}")
    private int maxPoolSize;
    @Value("${aot.aoc.api.executor.keepAliveSeconds:60}")
    private int keepAliveSeconds;

    private final BeanFactory beanFactory;

    public AsyncConfig(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    @Bean(name = "executor.async")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("AsyncTask-");

        return new LazyTraceThreadPoolTaskExecutor(beanFactory, executor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return this;
    }

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.warn("Failed to run method '{}' with params [{}] because '{}'.",
                method, ex.getMessage(), Joiner.on(", ")
                        .join(params), ex);
    }
}
