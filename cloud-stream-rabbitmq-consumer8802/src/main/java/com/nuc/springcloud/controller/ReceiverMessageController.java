package com.nuc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author LM
 * @date 2020/6/6 20:17
 * @destription:
 */
@Component
@EnableBinding(Sink.class)
public class ReceiverMessageController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void getMessage(Message<String> message)
    {
        System.out.println("消费者1——————————>>>>> 接收到的消息为"+message.getPayload()+"\t"+serverPort);
    }
}
