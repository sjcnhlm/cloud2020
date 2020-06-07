package com.nuc.springcloud.controller;

import com.nuc.springcloud.entities.CommonResult;
import com.nuc.springcloud.entities.Payment;
import com.nuc.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        System.out.println(result);
        if(result > 0)
        {
            return new CommonResult(200,"数据插入成功,serverPort"+serverPort,result);
        }else
        {
            return new CommonResult(404,"插入数据失败",null);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);
        System.out.println(payment);
        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort"+serverPort,payment);
        }else
        {
            return new CommonResult(404,"没有对应记录",null);
        }
    }

    @RequestMapping("/payment/lb")
    public String getServerPort()
    {
        return this.serverPort;
    }
}
