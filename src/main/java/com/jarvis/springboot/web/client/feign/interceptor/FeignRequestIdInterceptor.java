package com.jarvis.springboot.web.client.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class FeignRequestIdInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String uuid = UUID.randomUUID().toString();
        template.header("X-Request-Id", uuid);
        log.info("FeignClient X-Request-Id:{} for url:[{} {}].", uuid, template.method(), template.url());
    }
}
