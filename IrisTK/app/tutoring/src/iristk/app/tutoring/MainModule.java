package iristk.app.tutoring;

import iristk.system.Event;
import iristk.system.InitializationException;
import iristk.system.IrisModule;

public class InitializationModule extends IrisModule {
	/*Starts, stops and compares which is the better answer*/
	/*BE CAREFUL WITH SYNCHRONIZATION OF EVENTS*/
	/*TODO when typed ten where is it supposed to stop*/
	/*TODO see if synchro really useful*/
	private String tutoResponse = "";
	private String question = "";
	private Boolean tuto_synchro = false;
	private Boolean question_synchro = false;
	
	@Override
	public void onEvent(Event event) {
        if (event.triggers("monitor.system.start")) {
        	say_and_listen("Could you compute how much is 5 + 5");
        }else if(event.getName().equals("sense.user.stop")) {
        	say_and_listen("Bye!");
        	try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	System.exit(0);
        } else if (event.getName().equals("sense.send.tuto.response")) {
        	/*What the tutoring module responds to the question*/
        	if(event.has("text")) {
        		tutoResponse = event.getString("text");
        		tuto_synchro = true;
        		send();
        	}
        }else if (event.getName().equals("sense.user.type")) {
        	/*What the user has typed*/
        	if(event.has("text")) {
        		if(event.has("text")) {
            		question = event.getString("text");
            		question_synchro = true;
            	}
        	}
        }else if (event.getName().equals("sense.user.receive")) {
        	/*What we received from mq (answer to display)*/
        	if(event.has("text")) {
        		if (event.getString("text").equals("stop")) {
        			Event newEvent = new Event("sense.user.stop");
        			send(newEvent);
        		}else say_and_listen(event.getString("text"));
        	}
        }
    }
	
	/*Change here if you want to compare the answers of multiple modules*/
	private void send() {
		if (tuto_synchro && question_synchro) {
			try {	
				MessageQueue mq = new MessageQueue();
				mq.publish("test-exchange", "from_server", question+"====="+tutoResponse);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		tuto_synchro = false;
		question_synchro = false;
	}
	
	private void say_and_listen(String text) {
		Event newEvent = new Event("action.speech");
		newEvent.put("text", text);
		send(newEvent);
		
		Event newListenEvent = new Event("action.listen");
		//newListenEvent.put("endSilTimeout", 500);
		//newListenEvent.put("timeout", 8000);
		send(newListenEvent);
	}
	

    @Override
    public void init() throws InitializationException {
        // Initialize the module
    }
	
}
