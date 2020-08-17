package com.hdl.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建临时队列
                    exchange = @Exchange(value = "fanout",type = "fanout")
            )
    })
    public void recemsg1(String msg){
        System.err.println("comsumer1 ===================="+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建临时队列
                    exchange = @Exchange(value = "fanout",type = "fanout")
            )
    })
    public void recemsg2(String msg){
        System.err.println("comsumer1 ===================="+msg);
    }
}
