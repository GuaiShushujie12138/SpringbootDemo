package com.jarvis.springboot.web.response;

import java.util.Map;

/**
 * error response
 */
public class ErrorResponse extends GeneralResponse {

    public ErrorResponse(int status, String errorCode, String errorMessage) {
        super();
        super.status = status;
        super.errorMessage = errorMessage;
        super.errorCode = errorCode;
    }

    public ErrorResponse(int status, String errorCode, String errorMessage, Map<String, Object> meta) {
        this(status, errorCode, errorMessage);
        super.meta = meta;
    }
}
