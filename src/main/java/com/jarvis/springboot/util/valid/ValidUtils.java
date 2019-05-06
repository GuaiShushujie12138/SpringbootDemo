package com.jarvis.springboot.util.valid;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class ValidUtils {

    private static final Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$") ;

    private ValidUtils() {}

    public static boolean isUuid(String value) {
        return !StringUtils.isEmpty(value) && UUID_REGEX.matcher(value).matches();
    }
}
