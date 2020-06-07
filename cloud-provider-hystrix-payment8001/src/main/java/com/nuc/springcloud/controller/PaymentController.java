package com.nuc.springcloud.controller;

import com.nuc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id)
    {
        return paymentService.paymentInfo_ok(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id)
    {
        return paymentService.paymentInfo_Timeout(id);
    }

    //======服务熔断=====

    /**
     * 实现效果：
     *  浏览器输入id为正的时候正常显示
     *  在多次输入id为负数的请求之后，再请求id为正，这个时候会显示id为负数
     *  之后自己就访问正常。
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/breaker/{id}")
    public String paymentBreaker(@PathVariable("id") Integer id)
    {
        return paymentService.paymentCircuitBreaker(id);
    }
}
