package com.jarvis.springboot.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class HttpUtils {

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-REAL-IP",
            "X-FORWARDED-FOR"
    };
    private static final String UNKNOWN_IP = "unknown";

    /**
     * get client ip address list by request
     *
     * @param request
     * @return
     */
    public static List<String> getClientIpAddressList(HttpServletRequest request) {
        List<String> ipAddressList = Lists.newArrayList();

        for (String ipHeader : IP_HEADER_CANDIDATES) {
            String clientIp = request.getHeader(ipHeader);
            log.info("ip header [{}], ip address [{}]", ipHeader, clientIp);

            boolean clientIpIsValid = !StringUtils.isEmpty(clientIp) && !UNKNOWN_IP.equalsIgnoreCase(clientIp);
            if (!clientIpIsValid) continue;

            String[] ipAddressArray = clientIp.split(",");
            for (String ipAddress : ipAddressArray) {
                if (StringUtils.isEmpty(ipAddress)) continue;
                ipAddressList.add(ipAddress);
            }
        }

        // add remote address
        if (!StringUtils.isEmpty(request.getRemoteAddr())) {
            ipAddressList.add(request.getRemoteAddr());
        }

        return ipAddressList.stream().map(String::trim).collect(Collectors.toList());
    }

    private HttpUtils() {}
}
