package com.hdl.test;

import com.hdl.SpringbootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringbootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {
    //注入rabbitmqTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

    @Test
    public void testWork(){
        for (int i = 0; i <10 ; i++) {
            rabbitTemplate.convertAndSend("work",i+" : hello work");
        }
    }

    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("fanout", "", "fanout模型");
    }

    @Test
    public void testRouteDirect(){
        rabbitTemplate.convertAndSend("routedirect", "error", "route-direct模型的error信息");
    }

    @Test
    public void testRouteTopic(){
        rabbitTemplate.convertAndSend("routetopic", "user.save.info","route-topic模型的user.save.info信息");
    }
}
