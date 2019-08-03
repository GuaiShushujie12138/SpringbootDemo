package com.jarvis.springboot.web.verify.internal;

import com.jarvis.springboot.util.HttpUtils;
import com.jarvis.springboot.util.IPUtils;
import com.jarvis.springboot.web.exception.BusinessException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jarvis.springboot.web.exception.Constants.INTERNAL_ACCESS_ONLY_ERROR_CODE;
import static com.jarvis.springboot.web.exception.Constants.INTERNAL_ACCESS_ONLY_ERROR_MESSAGE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class InternalAccessOnlyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean needHandleAccessOnly = handler instanceof HandlerMethod
                && ((HandlerMethod) handler).getMethod().isAnnotationPresent(InternalAccessOnly.class);

        if (needHandleAccessOnly) {
            for (String clientIp : HttpUtils.getClientIpAddressList(request)) {
                if (IPUtils.isInteranlIpAddress(clientIp)) {
                    // 有一个 ip 是内网的就给过
                    return true;
                }
            }

            throw new BusinessException(UNAUTHORIZED.value(), INTERNAL_ACCESS_ONLY_ERROR_MESSAGE, INTERNAL_ACCESS_ONLY_ERROR_CODE);
        }

        return true;
    }
}
