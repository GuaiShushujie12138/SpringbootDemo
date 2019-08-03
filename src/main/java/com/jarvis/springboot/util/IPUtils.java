package com.jarvis.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.SubnetUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class IPUtils {

    private static final List<SubnetUtils> IPV4_PRIVATE_SUB_NETS;
    private static final SubnetUtils IPV4_LOOPBACK_SUB_NET;
    private static final List<String> IPV6_LOOPBACK_IPS;

    static {
        IPV4_PRIVATE_SUB_NETS =
                Arrays.asList(
                        "10.0.0.0/8",
                        "172.16.0.0/12",
                        "192.168.0.0/16",
                        "100.64.0.0/10"
                ).stream()
                        .map(SubnetUtils::new)
                        .collect(Collectors.toList());

        // 本地回环
        IPV4_LOOPBACK_SUB_NET = new SubnetUtils("127.0.0.0/8");

        IPV6_LOOPBACK_IPS = Arrays.asList("0:0:0:0:0:0:0:1", "::1");
    }

    public static boolean isInteranlIpAddress(String ipAddress) {
        return isPrivateIpAddress(ipAddress) || isLoopBackIpAddress(ipAddress);
    }

    private static boolean isLoopBackIpAddress(String ip) {
        boolean result;

        if (IPV6_LOOPBACK_IPS.contains(ip)) {
            result = true;
        } else {
            result = IPV4_LOOPBACK_SUB_NET.getInfo().isInRange(ip);
        }

        return result;
    }

    private static boolean isPrivateIpAddress(String ip) {
        boolean result;

        try {
            result = IPV4_PRIVATE_SUB_NETS.stream().anyMatch(n -> n.getInfo().isInRange(ip));
        } catch (Exception ex) {
            log.error("isPrivateIpAddress error, Ip {}, ex {}", ip, ex.getMessage(), ex);
            result = false;
        }

        return result;
    }

    private IPUtils() {
    }
}
