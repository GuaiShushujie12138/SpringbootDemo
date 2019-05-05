package com.jarvis.springboot.annotation.currentuser;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author: jarvis.fu
 * @CurrentUser resolver
 */
public class CurrentUserParameterResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return isCurrentUser(parameter);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 可从 session, token, cookie 等地获取 currentUser
        return User.builder().id(1L).name("user").build();
    }

    private boolean isCurrentUser(MethodParameter parameter) {
        CurrentUser currentUser = parameter.getParameterAnnotation(CurrentUser.class);
        return currentUser != null && User.class.equals(parameter.getParameterType());
    }
}
