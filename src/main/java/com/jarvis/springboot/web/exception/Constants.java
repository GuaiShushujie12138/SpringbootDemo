package com.jarvis.springboot.web.exception;

public class Constants {

    /**
     * error code
     */
    public static final String X_REQUEST_ID_IS_NULL_OR_NOT_UUID_ERROR_CODE = "error.validation.X-Request-Id.is_null_or_not_uuid";

    public static final String X_REQUEST_ID_IS_REPEATED_ERROR_CODE = "error.validation.X-Request-Id.is_repeated";

    /**
     * error message
     */
    public static final String X_REQUEST_ID_IS_NULL_OR_NOT_UUID_ERROR_MESSAGE = "X-Request-Id 为空或者不是 uuid 格式! request id : %s";

    public static final String X_REQUEST_ID_IS_REPEATED_ERROR_MESSAGE = "request id is repeated! request id : %s";

    private Constants() {}
}
