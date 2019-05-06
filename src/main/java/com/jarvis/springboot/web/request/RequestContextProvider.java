package com.jarvis.springboot.web.request;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface RequestContextProvider {

    /**
     * get current request
     *
     * @return
     */
    default HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * get path variables
     *
     * @return
     */
    default Map<String, Object> getPathVariables() {
        return (Map<String, Object>) getCurrentRequest().getAttribute(View.PATH_VARIABLES);
    }
}
