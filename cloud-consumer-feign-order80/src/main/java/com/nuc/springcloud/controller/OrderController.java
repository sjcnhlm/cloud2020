package com.nuc.springcloud.controller;

import com.nuc.springcloud.entities.CommonResult;
import com.nuc.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id)
    {
        return paymentFeignService.getPaymentById(id) ;
    }

    @GetMapping("/consumer/feign/timeout")
    public String paymentTimeout()
    {
        return paymentFeignService.paymentTimeout();
    }
}
