package directDemo;

import Utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//生产者
public class Provider {
    @Test
    public void testSendMesg() throws IOException, TimeoutException {
        /*
        * 1、创建连接mq的连接工厂对象
        * 2、设置连接rabbitmq的主机
        * 3、设置端口号
        * 4、设置连接虚拟机
        * 5、设置访问虚拟机的用户名和密码
        * 6、获取连接对象
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
        * 1、获取连接中通道对象
        * 2、通道对象绑定对应消息队列
        * 3、发布消息
        * 4、关闭连接
        * */
        Channel channel = connection.createChannel();
        /*   @参数1 ： 队列名称
         *   @参数2 ： 定义队列特性分割是需要持久化
         *   @参数3 ： 是否独占队列
         *   @参数4 ： 是否在消费完后自动删除队列
         *   @参数5 ： 额外附加参数
         */
        channel.queueDeclare("hello",false,false,false,null);
        /*
        * 参数1 ： 交换机名称
        * 参数2 ： 队列名称
        * 参数3 ： 传递消息的额外设置
        * 参数4 ： 消息的具体内容
        * */
        channel.basicPublish("", "hello", null, "hello rabbbitmqUtils".getBytes());
        /*channel.close();
        connection.close();*/

        //工具类：
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
