package com.nuc.springcloud.controller;

import com.nuc.springcloud.entities.CommonResult;
import com.nuc.springcloud.entities.Payment;
import com.nuc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
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

    /*
    实现服务发现
    * */
    @GetMapping("/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String service:services
             ) {
            log.info(service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentTimeout()
    {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }
}
