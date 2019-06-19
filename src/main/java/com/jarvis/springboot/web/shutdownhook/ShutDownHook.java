package com.jarvis.springboot.web.shutdownhook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 在程序退出的时候会调用 shutDownHook, 可以保证被意外 kill 的时候能继续执行一些操作
 */
@Slf4j
@Component
public class ShutDownHook {

    /**
     * 线程正在执行
     */
    private static final String PROCESSING = "PROCESSING";

    /**
     * 线程执行完成
     */
    private static final String PROCESS_FINISHED = "PROCESS_FINISHED";

    /**
     * 标识是否 shutDown
     */
    private volatile boolean isShutDown = false;

    /**
     * 存放当前存活的执行业务代码线程状态
     */
    private volatile Map<Long, String> aliveThreadStates = new ConcurrentHashMap<>();

    public ShutDownHook() {
        registerShutDownHook();
    }

    /**
     * 业务处理
     */
    public void dealBussiness() {
        if (isShutDown) {
            // shut down 之后不允许有新的业务处理
            return;
        }

        long currentThreadId = Thread.currentThread().getId();
        aliveThreadStates.put(currentThreadId, PROCESSING);

        try {
            doBusiness();
            aliveThreadStates.put(currentThreadId, PROCESS_FINISHED);
        } catch (Exception e) {
            log.error("doBusiness error", e.getMessage(), e);
        } finally {
            aliveThreadStates.remove(currentThreadId);
        }
    }

    private void doBusiness() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1000);
    }

    private void registerShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 标识已经 shutDown 了, 不让新的请求线程继续执行
            isShutDown = true;

            // log 当前收集到的所有 aliveThreadStates
            log.info("alive thread states, threadIds: , status: ", aliveThreadStates.keySet(), aliveThreadStates.values());

            long duration = 0;
            int count = 0;
            while (true) {
                count++;

                long currentTime = System.currentTimeMillis();

                // 查询是否存在当前正在执行业务的线程
                boolean foundAliveThread = aliveThreadStates.values().stream().anyMatch(state -> PROCESSING.equals(state));

                duration += System.currentTimeMillis() - currentTime;

                if (count % 3 == 0) {
                    log.info("alive thread states, threadIds: , status: ", aliveThreadStates.keySet(), aliveThreadStates.values());
                }

                if (!foundAliveThread) {
                    // 业务线程全部执行完成, 那么就跳出循环
                    break;
                }
            }

            log.info("shut down 之后, 仍运行的时间(单位 ms): ", duration);
            try {
                // do something
                doSomething();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                //
            }
        }));
    }

    private void doSomething() {
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            log.info("sleep is interrupted", e.getMessage(), e);
        }
    }
}
