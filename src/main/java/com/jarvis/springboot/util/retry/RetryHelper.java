package com.jarvis.springboot.util.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: jarvis.fu
 * <p>
 * 支持类中调用内部方法时, retry 生效
 */
@Component
public class RetryHelper {

    /**
     * @param consumer
     * @param t
     * @param <T>
     * @Backoff(delay = 1000L, multiplier = 2) 代表延时重试, 1s, 2s, 4s, 8s 这样的时间点重试
     */
    @Retryable(value = Exception.class, backoff = @Backoff(delay = 1000L, multiplier = 2))
    public <T> void retry(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

    @Retryable(value = Exception.class, backoff = @Backoff(delay = 1000L, multiplier = 2))
    public <R> R retry(Supplier<R> supplier) {
        return supplier.get();
    }

    @Retryable(value = Exception.class, backoff = @Backoff(delay = 1000L, multiplier = 2))
    public <T, R> R retry(Function<T, R> function, T t) {
        return function.apply(t);
    }
}