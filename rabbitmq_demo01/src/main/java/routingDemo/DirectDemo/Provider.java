package routingDemo.DirectDemo;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //通过通道声明交换机
        channel.exchangeDeclare("directdemo", "direct");

        String routingkey = "error";

        channel.basicPublish("directdemo", routingkey, null, "routing-direct mesg".getBytes());

        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
