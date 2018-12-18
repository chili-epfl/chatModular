package iristk.app.tutoring;

import java.util.Formatter;

import iristk.system.Event;
import iristk.system.InitializationException;
import iristk.system.IrisModule;

public class TutoringModule extends IrisModule {
	
	private Integer number;
	private Integer guesses;
	private Grammar g = new Grammar();

	

	@Override
	public void onEvent(Event event) {
        if (event.triggers("monitor.system.start")) {
        	number = 10;
        	guesses = 0;
        	//say_and_listen("Could you compute how much is 5 + 5", false);
        }else if (event.getName().equals("sense.speech.rec")) { 
        	/*Check what the user says*/
            if(event.has("text")) {
            	check(event.getString("text"));
            }
        }else if (event.getName().equals("sense.user.type")) {
        	/*Check what the user typed*/
        	if(event.has("text")) {
        		check(event.getString("text"));
        	}
        }
    }
	
	
	private void check(String answer) {
		StringBuilder sbuf = new StringBuilder();
		Formatter fmt = new Formatter(sbuf);
		if (g.getGrammar(answer) == null) {
			//add lower and upper cases
			if (answer.contains("help") || answer.contains("don't know")) {
				String prefix = "";
				if(answer.contains("help")) prefix = "You need some help?";
				else if (answer.contains("don't know")) prefix = "You don't know?";
				String matching_response = g.getRandomResponse();
				fmt.format("%s %s", prefix, matching_response);
				//send_tuto_response(sbuf.toString());
			} else send_tuto_response("I am sorry, I didn't get that.");
		} else {
			guesses++;
			if (g.getGrammar(answer) == number) {			//a number was typed
				if (guesses == 1) {
					fmt.format("%s is correct, you find it on the first try", answer);
					//send_tuto_response(sbuf.toString());
				} else {
					fmt.format("%s is correct. Great! You've found it this time.", answer);
					//send_tuto_response(sbuf.toString());
				}
			} else if (g.getGrammar(answer) == (number + 1) || g.getGrammar(answer) == (number - 1) ) {
				fmt.format("%s is almost correct! Let's try one more time.", answer);
				//send_tuto_response(sbuf.toString());
			}else if(g.getGrammar(answer) > number) {
		    	fmt.format("%s is too high, think again.", answer);
		    	//send_tuto_response(sbuf.toString());
		    }else {
		    	fmt.format("%s is too low, think again.", answer);
		    	//send_tuto_response(sbuf.toString());
		    }
		}
		send_tuto_response(sbuf.toString());
		fmt.close();
	}
	
	//We can send either an answer or an information from the system
	private void send_tuto_response(String text) {
		//Send an event that the forum from TutoringModule is ready to be compared
		System.out.println("--------------------------------------------------------");
		System.out.println("SENT: " + text);
		Event newEvent = new Event("sense.send.tuto.response");
		newEvent.put("text", text);
		send(newEvent);
	}
	

    @Override
    public void init() throws InitializationException {
        // Initialize the module
    }
	
}
