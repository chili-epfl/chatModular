package iristk.app.tutoring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer {
	
	private List<String> messages = new ArrayList<String>();

	public static void serverConsume() throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    //factory.setHost("localhost");
	    //factory.setUri("amqp://meretm:o8!jk6f.@128.179.191.225:5672/test");		 //at epfl
	    factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.19:5672/test");		//at home
	 
	    //factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.19:5672/test");		//IP of this computer and its vhost
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare("test-queue", true, false, false, null);

	    DefaultConsumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	          String message = new String(body, "UTF-8");
	          System.out.println("Server received '" + message + "'");
	        }
	      };
	  
	    channel.basicConsume("test-queue", true, consumer);
	}
	
	public static void clientConsume() throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    //factory.setHost("localhost");
	    //factory.setUri("amqp://meretm:o8!jk6f.@128.179.191.225:5672/test");		//at epfl
	    factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.19:5672/test");		//at home
	      
	    //factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.20:5672/test_bis"); 	//change IP and vhost vhost (other computer) => check why not other user and password	
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare("test-queue", true, false, false, null);

	    DefaultConsumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	          String message = new String(body, "UTF-8");
	          System.out.println("Client received '" + message + "'");
	        }
	      };
	  
	    channel.basicConsume("test-queue", true, consumer);
	}
	
	/*public List<String> getMessages(){
		return messages;
	}
	
	public void updateMessages(int i) {
		messages
	}*/

}
