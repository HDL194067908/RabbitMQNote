package routingDemo.TopicDemo;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //绑定交换机
        channel.exchangeDeclare("directdemo", "direct");
        //临时队列
        String queueName = channel.queueDeclare().getQueue();
        //基于routingkey绑定交换进和队列
        channel.queueBind(queueName, "directdemo","info");
        //消费信息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer-info : "+new String(body));
            }
        });
    }
}
