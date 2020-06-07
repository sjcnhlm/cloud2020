package com.nuc.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id)
    {
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_ok:id"+id+"\t"+"欧克";
    }

    /**
     *
     *   一旦调用服务方法失败并抛出了错误信息后，
     *   会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
     *
     *  并且设置这个线程的超时时间是3s，3s内是正常的业务逻辑，超过3s调用fallbackMethod指定的方法进行处理
     */

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",
            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    public String paymentInfo_Timeout(Integer id)
    {
        int timeNumber = 5;
        //int age = 10/0;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_Timeout,id："+id+"\t"+"O(∩_∩)O哈哈~"+"   耗时(秒)：";

    }


    public String paymentInfo_TimeoutHandler(Integer id)
    {
        return "线程池"+Thread.currentThread().getName()+"  程序运行错误或者超时 ，请稍后再试"+id;
    }

    //=======服务熔断=======

    /**
     * 该方法实现了 如果在十秒钟，10次请求次数中失败率达到了60% 就会跳闸。
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties= {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"), //是否开启熔断器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"), // 请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //窗口期 10秒
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到60% 跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {

        if(id < 0)
        {
            throw new RuntimeException("*******id 不能为负数*******");
        }

        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号为:"+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能为负数" + id;
    }
}
