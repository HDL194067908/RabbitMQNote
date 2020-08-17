package workqueuesDemo;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只能消费一个消息
        channel.queueDeclare("work", true, false, false, null);
        channel.basicConsume("work", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2： "+ new String(body));
                //手动确认
                /*
                * 参数1 ： 手动确认消息标识
                * 参数2 ： 是否开启多个消息确认， false 每次确认一个
                * */
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
