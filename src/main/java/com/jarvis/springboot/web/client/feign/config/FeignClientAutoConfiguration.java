package com.jarvis.springboot.web.client.feign.config;

import com.jarvis.springboot.web.client.feign.interceptor.FeignRequestIdInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.jarvis.springboot.web.client.feign"})
@EnableConfigurationProperties(FeignClientEncodingProperties.class)
@AutoConfigureAfter(FeignAutoConfiguration.class)
public class FeignClientAutoConfiguration {

    @Bean
    public FeignRequestIdInterceptor feignRequestIdInterceptor() {
        return new FeignRequestIdInterceptor();
    }
}
