package Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;
    static {
        // 重量级资源 ，类加载只执行一次
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/demo");
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("123456");
    }
    //定义提供连接对象
    public static Connection getConnection() {
        try {

            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭连接工具方法
    public static void closeConnectionAndChannel(Channel channel,Connection connection){
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
