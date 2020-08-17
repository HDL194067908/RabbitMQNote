package directDemo;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        /*
         * 1、创建连接工厂
         * 2、设置连接rabbitmq
         * 3、设置连接虚拟主机和端口号
         * 4、设置连接用户和密码
         * 5、创建连接对象
         * */
       /* ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/demo");
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("123456");
        Connection connection = connectionFactory.newConnection();*/

        //工具类：
        Connection connection = RabbitMQUtils.getConnection();
        /*
         * 1、创建通道
         * 2、通道绑定对象
         * 3、消费信息
         * 4、关闭连接
         * */
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", false, false, false, null);

        /*
         * 参数1 ： 队列名称
         * 参数2 ： 开始消息的自动确认机制
         * 参数3 ： 消费时的回调接口
         * */
        channel.basicConsume("hello", true , new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(Body) = "+ new String(body));
            }
        });
        /*channel.close();
        connection.close();*/

    }

}
