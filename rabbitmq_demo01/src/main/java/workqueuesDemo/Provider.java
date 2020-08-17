package workqueuesDemo;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int i= 0 ; i<50 ;i++){
            channel.basicPublish("", "work", null, (i+" : hello workqueues").getBytes());
        }
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
