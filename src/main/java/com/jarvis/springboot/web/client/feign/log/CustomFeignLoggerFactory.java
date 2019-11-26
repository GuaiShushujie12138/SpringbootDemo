package com.jarvis.springboot.web.client.feign.log;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

public class CustomFeignLoggerFactory implements FeignLoggerFactory {

    @Override
    public Logger create(Class<?> type) {
        return new FeignAggregatedLogger(type);
    }
}
