package com.hdl.route_topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteTopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "routetopic",type = "topic"),
                    key = {"user.*","user.save"}
            )
    })
    public void recemsg1(String msg){
        System.err.println("consumer1 ====================== "+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "routetopic",type = "topic"),
                    key = {"user.#"}
            )
    })
    public void recemsg2(String msg){
        System.err.println("consumer2 ====================== "+msg);
    }
}
