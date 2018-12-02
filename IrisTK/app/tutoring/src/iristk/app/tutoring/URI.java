package iristk.app.tutoring;

public final class URI {
	
	private final static String username = "meretm";
	private final static String pwd = "o8!jk6f.";
	private final static String ip = "192.168.1.46";
	//private final static String ip = "192.168.0.19";		//home ip
	private final static String port = "5672";
	private final static String vhost = "test";
	
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
