package iristk.app.tutoring;

/**
 * Creates the identifier to connect to RabbitMQ
 */
public final class URI {
	
	/**
	 * Change these parameters to be able to connect
	 */
	private final static String username = "";
	private final static String pwd = "";
	private final static String ip = "";
	private final static String port = "5672";
	private final static String vhost = "";
	
	public static String getUri() {
		StringBuilder sb = new StringBuilder();
		sb.append("amqp://");
		sb.append(username);
		sb.append(":");
		sb.append(pwd);
		sb.append("@");
		sb.append(ip);
		sb.append(":");
		sb.append(port);
		sb.append("/");
		sb.append(vhost);
		
		return sb.toString();
	}

	
	
	

}
