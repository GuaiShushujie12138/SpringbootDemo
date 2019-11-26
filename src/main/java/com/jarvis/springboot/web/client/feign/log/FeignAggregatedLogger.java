package com.jarvis.springboot.web.client.feign.log;


import feign.Request;
import feign.Response;
import feign.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static feign.Util.decodeOrDefault;
import static feign.Util.valuesOrEmpty;

public class FeignAggregatedLogger extends feign.Logger {

    private final Logger logger;

    public FeignAggregatedLogger() {
        this(feign.Logger.class);
    }

    public FeignAggregatedLogger(Class<?> clazz) {
        this(LoggerFactory.getLogger(clazz));
    }

    public FeignAggregatedLogger(String name) {
        this(LoggerFactory.getLogger(name));
    }

    FeignAggregatedLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) { //NOSONAR
        if (logger.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append(format("---> %s %s HTTP/1.1 %n", request.httpMethod().name(), request.url()));

            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

                for (String field : request.headers().keySet()) {
                    for (String value : valuesOrEmpty(request.headers(), field)) {
                        builder.append(format("%s: %s %n", field, value));
                    }
                }

                int bodyLength = 0;
                Request.Body body = request.requestBody();
                if (body != null) {
                    bodyLength = body.length();
                    if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                        String bodyText = body.asString();
                        builder.append(format("Request Body: %n")).append(format("%s %n", bodyText != null ? bodyText : "Binary data"));
                    }
                }
                builder.append(format("---> END HTTP (%s-byte body) %n", bodyLength));
            }
            log(configKey, builder.toString());
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) //NOSONAR
            throws IOException {
        if (logger.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            String reason =
                    response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason()
                            : "";
            int status = response.status();
            builder.append(format("<--- HTTP/1.1 %s%s (%sms) %n", status, reason, elapsedTime));
            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

                for (String field : response.headers().keySet()) {
                    for (String value : valuesOrEmpty(response.headers(), field)) {
                        builder.append(format("%s: %s %n", field, value));
                    }
                }

                int bodyLength = 0;
                Response.Body body = response.body();
                boolean hasBody = body != null && !(status == 204 || status == 205);
                if (hasBody) {
                    // HTTP 204 No Content "...response MUST NOT include a message-body"
                    // HTTP 205 Reset Content "...response MUST NOT include an entity"
                    if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                        builder.append(format("Response Body: %n"));
                    }
                    byte[] bodyData = Util.toByteArray(body.asInputStream());
                    bodyLength = bodyData.length;
                    if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyLength > 0) {
                        builder.append(format("%s %n", decodeOrDefault(bodyData, Util.UTF_8, "Binary data")));
                    }
                    builder.append(format("<--- END HTTP (%s-byte body) %n", bodyLength));
                    log(configKey, builder.toString());
                    return response.toBuilder().body(bodyData).build();
                } else {
                    builder.append(format("<--- END HTTP (%s-byte body) %n", bodyLength));
                }
            }
            log(configKey, builder.toString());
            return response;
        }
        return response;
    }

    /**
     * 打印log
     *
     * @param configKey
     * @param content   需要打印的内容
     * @param args      不要传入
     */
    @Override
    protected void log(String configKey, String content, Object... args) { //NOSONAR
        if (logger.isDebugEnabled()) {
            logger.debug(format("%s %n %s", methodTag(configKey), content));
        }
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }
}
