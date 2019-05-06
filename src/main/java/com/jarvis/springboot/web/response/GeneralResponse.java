package com.jarvis.springboot.web.response;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * general response
 */
@Data
@NoArgsConstructor
public class GeneralResponse<T> {

    private T data;
    protected int status = HttpStatus.OK.value();
    protected Map<String, Object> meta = Maps.newHashMap();
    protected String errorMessage;
    protected String errorCode;

    public GeneralResponse(T data) {
        this.data = data;
    }
}
