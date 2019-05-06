package com.jarvis.springboot.web.client.feign;

import com.jarvis.springboot.annotation.currentuser.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "myFeignClient", url = "http://localhost:8080")
public interface MyFeignClient {

    @GetMapping(value = "/v1/user/byId")
    User getUserById(Long userId);
}
