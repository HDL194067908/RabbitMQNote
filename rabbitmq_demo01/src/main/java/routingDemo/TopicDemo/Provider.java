package routingDemo.TopicDemo;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //通过通道声明交换机
        channel.exchangeDeclare("topicdemo", "topic");

        String routingkey = "user.save.test";

        channel.basicPublish("topicdemo", routingkey, null, "routing-topic mesg".getBytes());

        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
