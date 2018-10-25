package iristk.app.tutoring;

import java.io.IOException;

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
import iristk.system.IrisSystem;

public class MessageQueue extends IrisModule {

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private String queueName;
	private Consumer consumer;
	private Integer number;
	private Integer guesses;
	private Grammar g = new Grammar();
	
	public MessageQueue() throws Exception {
		 factory = new ConnectionFactory();
		 factory.setUri(URI.getUri());
		   
		 connection = factory.newConnection();
		 channel = connection.createChannel();
		   
		 channel.exchangeDeclare("test-exchange", "direct", true);
	}
	
	public void publish(String exchange, String routing_key, String msg) throws Exception {
		channel.basicPublish(exchange, routing_key, null, msg.getBytes("UTF-8"));
		System.out.println("Server sent '" + msg + "'");
		
		channel.close();
		connection.close();
	}
	
	public void bindQueue(String exchange, String routing_key) throws Exception {
		queueName = channel.queueDeclare().getQueue();
	    channel.queueBind(queueName, exchange, routing_key);

	    consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	          String message = new String(body, "UTF-8");
	          System.out.println("Client received '" + message);
	          receive(message);
	          //channel.basicAck(envelope.getDeliveryTag(), false);
	        }
	        
	      };
	}
	
	public void consume() throws Exception {
		 boolean autoAck = false;
		 channel.basicConsume(queueName, autoAck, consumer);
	}
	
	//Event I'd like to send with the text received
	private void receive(String text) {
		Event newEvent = new Event("sense.user.receive");
    	newEvent.put("text", text);
		send(newEvent);					//Never works because system is null, why?
	}
	
	
	@Override
	public void onEvent(Event event) {
        if (event.getName().equals("sense.user.receive")) {
			System.out.println("Has received the event!");
		}
    }

	@Override
	public void init() throws InitializationException {
		// TODO Auto-generated method stub
		
	}
	
	
}
	
