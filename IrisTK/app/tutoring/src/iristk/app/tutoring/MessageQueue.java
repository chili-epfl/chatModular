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

/**
 * MessageQueue to connect, send and receive messages with RabbitMQ
 */
public class MessageQueue extends IrisModule {

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private String queueName;
	private Consumer consumer;

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
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Client received '" + message);
				receive(message);
			}

		};
	}

	public void consume() throws Exception {
		boolean autoAck = false;
		channel.basicConsume(queueName, autoAck, consumer);
	}

	/**
	 * Create an event with the text that has just been received with RabbitMQ
	 * messaging
	 * 
	 * @param text: the text to be put in the event
	 */
	private void receive(String text) {
		Event newEvent = new Event("sense.receive");
		newEvent.put("text", text);
		send(newEvent);
	}

	@Override
	public void onEvent(Event event) {
	}

	@Override
	public void init() throws InitializationException {
		// Initialize the module
	}

}
