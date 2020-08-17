package fanoutDemo;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        /*
        * 将通道声明指定交换机
        * 参数1 ： 交换机名称
        * 参数2 ： 交换机类型*/
        channel.exchangeDeclare("fanoutdemo", "fanout");
        /*
        * 发送消息
        * */
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("fanoutdemo", "", null, (i + " : fanout type message").getBytes());
        }
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
