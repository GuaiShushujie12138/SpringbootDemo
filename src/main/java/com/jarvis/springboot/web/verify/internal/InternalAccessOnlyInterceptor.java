package com.jarvis.springboot.web.verify.internal;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InternalAccessOnlyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean needHandleAccessOnly = handler instanceof HandlerMethod
                && ((HandlerMethod) handler).getMethod().isAnnotationPresent(InternalAccessOnly.class);

        if (needHandleAccessOnly) {

        }

        return true;
    }
}
