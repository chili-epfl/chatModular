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
        }else if (event.getName().equals("sense.user.stop")) {
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	System.exit(0);
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
