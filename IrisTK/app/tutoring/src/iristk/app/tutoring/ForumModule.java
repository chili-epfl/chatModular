package iristk.app.tutoring;

import iristk.system.Event;
import iristk.system.InitializationException;
import iristk.system.IrisModule;

public class ForumModule extends IrisModule {
	
	@Override
	public void onEvent(Event event) {
        if (event.triggers("monitor.system.start")) {
        	say("You can ask a question.");
        	//Event newEvent = new Event("action.waitForSpeech");
        }else if (event.getName().equals("sense.user.type")) {
        	if(event.has("text")) {
        		System.out.println("You have typed something");
        	}
        }else if (event.getName().equals("sense.user.receive")) {
        	if(event.has("text")) {
        		say("Do you have another question?");
        	}
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
