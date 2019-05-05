package com.jarvis.springboot.util.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: jarvis.fu
 * <p>
 * 支持类中调用内部方法时, 事务生效
 */
@Component
public class TransactionHelper {

    @Transactional(rollbackFor = Exception.class)
    public <T> void openTansaction(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

    @Transactional(rollbackFor = Exception.class)
    public <R> R openTansaction(Supplier<R> supplier) {
        return supplier.get();
    }

    @Transactional(rollbackFor = Exception.class)
    public <T, R> R openTansaction(Function<T, R> function, T t) {
        return function.apply(t);
    }
}
