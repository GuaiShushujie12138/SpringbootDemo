package com.jarvis.springboot.web.exception;

import com.jarvis.springboot.web.response.ErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(1)
@Component
@ControllerAdvice
public class GlobalControllerException {

    @ExceptionHandler(BusinessException.class)
    public ErrorResponse handleBusinessException(BusinessException e) {
        return new ErrorResponse(e.getStatus(), e.getErrorCode(), e.getErrorMessage());
    }
}
