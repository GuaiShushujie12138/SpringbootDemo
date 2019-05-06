package com.jarvis.springboot.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * http状态
     */
    private int status;

    /**
     * 自定义错误消息
     */
    private String errorMessage;

    /**
     * 错误code
     */
    private String errorCode;
}
