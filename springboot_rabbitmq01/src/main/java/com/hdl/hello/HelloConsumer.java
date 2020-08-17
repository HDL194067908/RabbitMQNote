package com.hdl.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/*
* 定义队列特性
* 默认 ： 持久化 非独占 不自动删除
* @Queue( ... )*/
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloConsumer {
    @RabbitHandler
    public void recemsg(String msg){
        System.err.println("===================="+msg);
    }
}
