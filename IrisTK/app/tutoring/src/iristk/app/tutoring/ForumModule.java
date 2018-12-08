package iristk.app.tutoring;

import iristk.system.Event;
import iristk.system.InitializationException;
import iristk.system.IrisModule;

public class ForumModule extends IrisModule {
	
	@Override
	public void onEvent(Event event) {
        if (event.triggers("monitor.system.start")) {
        	//say("You can ask a question.");
        /*}else if (event.getName().equals("sense.user.receive")) {
        	if(event.has("text")) {
        		say("Do you have another question?");
        	}*/
        }else if (event.getName().equals("sense.speech.rec")) {
            /*Check what the user says*/
        	if(event.has("text")) {
            sendMessage(event.getString("text"));
                }
        }else if (event.getName().equals("sense.user.stop")) {
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	System.exit(0);
        }
    }
	
	private void sendMessage(String message) {
		MessageQueue mq;
		try {
			mq = new MessageQueue();
			mq.publish("test-exchange", "from_server", message);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void say(String text) {
		Event newEvent = new Event("action.speech");
    	newEvent.put("text", text);
		send(newEvent);
	}

    @Override
    public void init() throws InitializationException {
        // Initialize the module
    }
	
}
