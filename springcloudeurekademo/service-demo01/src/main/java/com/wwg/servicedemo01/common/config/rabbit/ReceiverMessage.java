package com.wwg.servicedemo01.common.config.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "oneMessageQueue")
public class ReceiverMessage {
    @RabbitHandler
    public void process(String mess){
        System.out.println("Receiver message:"+mess);
    }
}
