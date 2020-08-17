package com.hdl.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {

    /*
    * 第一个消费者*/
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void recemsg1(String msg){
        System.err.println("comsumer1 ===================="+msg);
    }

    /*
    * 第二个消费者*/
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void recemsg2(String msg){
        System.err.println("comsumer2 ===================="+msg);
    }


}
