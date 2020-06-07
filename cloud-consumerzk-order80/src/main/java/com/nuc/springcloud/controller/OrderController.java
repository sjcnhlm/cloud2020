package com.nuc.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController
{
    private static final String PAYMENT_URL =  "http://cloud-provider-payment"; //通过在eureka上注册过的微服务名称调用

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/get")
    public String getPayment()
    {
        String result = restTemplate.getForObject(PAYMENT_URL + "/zk/get", String.class);

        return result;
    }

}
