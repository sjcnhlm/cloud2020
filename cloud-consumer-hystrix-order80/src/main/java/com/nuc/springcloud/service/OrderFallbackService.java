package com.nuc.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "您要找的服务已经宕机 请稍后重试";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "您要找的服务已经宕机 请稍后重试";
    }
}
