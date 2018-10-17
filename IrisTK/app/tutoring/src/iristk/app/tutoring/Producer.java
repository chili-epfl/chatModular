package iristk.app.tutoring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	
	public static void serverPublish(String msg) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		//factory.setHost("localhost");
		//factory.setUri("amqp://meretm:o8!jk6f.@128.179.191.225:5672/test");		//at epfl
		factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.19:5672/test");		//at home
	
		//factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.20:5672/test_bis");	//change ip and vhost (other computer)
		factory.setConnectionTimeout(300000);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("test-queue", true, false, false, null);
		channel.basicPublish("", "test-queue", null, msg.getBytes("UTF-8"));
		System.out.println("Server sent '" + msg + "'");
	}
	
	public static void clientPublish(String msg) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		//factory.setHost("localhost");
		//factory.setUri("amqp://meretm:o8!jk6f.@128.179.191.225:5672/test");		//at epfl	
		factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.19:5672/test");		//at home
		
		//factory.setUri("amqp://m:m@128.179.186.104:5672/test");		//IP of this computer and this vhost, change user and password
		factory.setConnectionTimeout(300000);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("test-queue", true, false, false, null);
		channel.basicPublish("", "test-queue", null, msg.getBytes("UTF-8"));
		System.out.println("Client sent '" + msg + "'");
	}
}
