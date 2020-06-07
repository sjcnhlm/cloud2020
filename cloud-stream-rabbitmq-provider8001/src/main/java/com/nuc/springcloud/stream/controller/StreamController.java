package com.nuc.springcloud.stream.controller;

import com.nuc.springcloud.stream.service.IMessageSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LM
 * @date 2020/6/6 19:58
 * @destription:
 */
@RestController
public class StreamController {
    @Resource
    private IMessageSender iMessageSender;

    @GetMapping("/sendMessage")
    public String sendMessage()
    {
        return iMessageSender.send();
    }
}
