package com.jarvis.springboot.web.verify.repeat;

import com.jarvis.springboot.util.valid.ValidUtils;
import com.jarvis.springboot.web.exception.BusinessException;
import com.jarvis.springboot.web.request.RequestContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;

import static com.jarvis.springboot.web.exception.Constants.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Aspect
@Component
public class BlockRepeatedSubmitAspect implements RequestContextProvider {

    private static final String X_REQUEST_ID = "X-Request-Id";

    @Value("${springboot.demo.request_id.ttl:1s}")
    private Duration requestIdTtl;

    @Value("${springboot.demo.redis.key.namespace:springboot}")
    private String redisKeyNameSpace;

    private final RedisTemplate redisTemplate;

    public BlockRepeatedSubmitAspect(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Before("@annotation(com.jarvis.springboot.web.verify.repeat.BlockRepeatedSubmit)")
    public void doBefore() {
        String requestId = getCurrentRequest().getHeader(X_REQUEST_ID);

        if (StringUtils.isEmpty(requestId) || !ValidUtils.isUuid(requestId)) {
            log.warn(String.format(X_REQUEST_ID_IS_NULL_OR_NOT_UUID_ERROR_MESSAGE, requestId));
            throw new BusinessException(BAD_REQUEST.value(),
                    String.format(X_REQUEST_ID_IS_NULL_OR_NOT_UUID_ERROR_MESSAGE, requestId), X_REQUEST_ID_IS_NULL_OR_NOT_UUID_ERROR_CODE);
        }

        if (isRepeatedRequest(requestId)) {
            log.warn(String.format(X_REQUEST_ID_IS_REPEATED_ERROR_MESSAGE, requestId));
            throw new BusinessException(BAD_REQUEST.value(),
                    String.format(X_REQUEST_ID_IS_REPEATED_ERROR_MESSAGE, requestId), X_REQUEST_ID_IS_REPEATED_ERROR_CODE);
        }
    }

    /**
     * 判断 requestId 是否为重复请求
     * redis 设值失败即为重复请求
     *
     * @param requestId
     * @return
     */
    private boolean isRepeatedRequest(String requestId) {
        return !redisTemplate.opsForValue().setIfAbsent(getRequestIdCacheKey(requestId), requestId, requestIdTtl);
    }

    /**
     * generate requestId cache key in redis
     *
     * @param requestId
     * @return
     */
    private String getRequestIdCacheKey(String requestId) {
        return redisKeyNameSpace + ":" + X_REQUEST_ID + ":" + requestId;
    }
}
