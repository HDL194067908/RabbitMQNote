package com.hdl.route_dirrect;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteDirectConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建临时队列
                    exchange = @Exchange(value = "routedirect",type = "direct"),
                    key = {"info","error","warn"}
            )
    })
    public void recemsg1(String msg){
        System.err.println("comsumer1 ===================="+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建临时队列
                    exchange = @Exchange(value = "routedirect",type = "direct"),
                    key = {"error",}
            )
    })
    public void recemsg2(String msg){
        System.err.println("comsumer2 ===================="+msg);
    }
}
