package com.jarvis.springboot.util.async;

import com.jarvis.springboot.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class AsyncHelper {

    /**
     * 线程配置
     */
    public static final Long EXECUTOR_DEFAULT_TIMEOUT_MILLISECONDS = 3000L;
    public static final String DEFAULT_EXECUTOR_BEAN_NAME = "executor.async";

    /**
     * 线程池
     */
    private static Executor executor;

    private AsyncHelper() {
        // async helper
    }

    public static CompletableFuture<Void> createFuture(String action, Runnable runnable) {
        return CompletableFuture.runAsync(runnable, executor)
                .exceptionally(ex -> {
                    log.error("Failed to exec async task [{}];because of ", action, ex);
                    return null;
                });
    }

    public static <T> T executeFuture(CompletableFuture<T> future) {
        return executeFuture(future, null, null);
    }

    /**
     * 在N毫秒内，执行任务获取结果
     * 执行异常打印log，返回null
     *
     * @param future       需要执行的任务
     * @param timeoutMills 超时的毫秒数，传null时使用系统默认值
     * @param <T>          执行结果对象
     * @return
     */
    public static <T> T executeFuture(CompletableFuture<T> future, Long timeoutMills) {
        return executeFuture(future, timeoutMills, null);
    }

    /**
     * 在N毫秒内，执行任务获取结果
     * 执行异常打印log，返回默认值defaultValue
     *
     * @param future       需要执行的任务
     * @param timeoutMills 超时的毫秒数，传null时使用系统默认值
     * @param defaultValue 异常默认值
     * @param <T>          执行结果对象
     * @return
     */
    public static <T> T executeFuture(CompletableFuture<T> future, Long timeoutMills, T defaultValue) {
        T result = null;

        if (future == null) {
            //空future返回默认值
            return defaultValue;
        }

        if (timeoutMills == null) {
            timeoutMills = EXECUTOR_DEFAULT_TIMEOUT_MILLISECONDS;
        }

        try {
            result = future.get(timeoutMills, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | TimeoutException e) {
            log.error("Executing future failed;cause by ", e);
        } catch (InterruptedException ie) {
            log.error("Current thread was interrupted;cause by ", ie);
            Thread.currentThread().interrupt();
        }
        return result;
    }

    static {
        String beanName = DEFAULT_EXECUTOR_BEAN_NAME;
        executor = ForkJoinPool.commonPool();
        try {
            executor = SpringContextUtils.getBean(beanName, Executor.class);
        } catch (BeansException e) {
            log.warn("Can't find spring bean for Executor(beanName={}).", beanName);
        } catch (Exception e) {
            log.warn("Can't generate async thread pool,will use default thread pool.");
        }
    }
}
