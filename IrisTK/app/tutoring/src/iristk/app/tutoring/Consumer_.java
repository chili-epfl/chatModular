package iristk.app.tutoring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import iristk.system.Event;
import iristk.system.InitializationException;
import iristk.system.IrisModule;

public class Consumer_ extends IrisModule{
	
	public void serverConsume() throws Exception {
		
	    ConnectionFactory factory = new ConnectionFactory();
	    //factory.setHost("localhost");
	    factory.setUri(URI.getUri());
	    //factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.19:5672/test");		//IP of this computer and its vhost
	   
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	   
	    //channel.exchangeDeclare("test-exchange", "direct", true);
	    //String queueName = channel.queueDeclare().getQueue();
	    //channel.queueBind(queueName, "test-exchange", "from_client");
	    channel.queueDeclare("test-queue", true, false, false, null);
	    

	    Consumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	          String message = new String(body, "UTF-8");
	          System.out.println("Server received '" + message);
	          type(message);
	        }
	      };
	      
	    //channel.basicConsume(queueName, true, consumer);
	    channel.basicConsume("test-queue", true, consumer);
	}
	
	public void clientConsume() throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    //factory.setHost("localhost");
	    factory.setUri(URI.getUri());  
	    //factory.setUri("amqp://meretm:o8!jk6f.@192.168.0.20:5672/test_bis"); 	//change IP and vhost (other computer) => check why not other user and password	
	    
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    //channel.exchangeDeclare("test-exchange", "direct", false);
	    //String queueName = channel.queueDeclare().getQueue();
	    //channel.queueBind(queueName, "test-exchange", "from_server");
	    channel.queueDeclare("test-queue", true, false, false, null);

	    Consumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	          String message = new String(body, "UTF-8");
	          System.out.println("Client received '" + message);
	        }
	      };
	  
	    //channel.basicConsume(queueName, true, consumer);
	    channel.basicConsume("test-queue", true, consumer);
	}

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws InitializationException {
		// TODO Auto-generated method stub
		
	}
	
	private void type(String text) {
		Event newEvent = new Event("sense.user.type");
    	newEvent.put("text", text);
		send(newEvent);
		//System.out.println("SENT");
	}

}
