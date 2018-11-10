package iristk.app.tutoring;

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
        	say("Could you compute how much is 5 + 5");
        	//Event newEvent = new Event("action.waitForSpeech");
        }else if (event.getName().equals("sense.user.type")) {
        	if(event.has("text")) {
        		check(event.getString("text"));
        	}
        }else if (event.getName().equals("sense.user.receive")) {
        	if(event.has("text")) {
        		check(event.getString("text"));
        	}
        }
    }
	
	
	private void check(String answer) {
		if (g.getGrammar(answer) == null) {
			if(answer.contains("help") || answer.contains("don't know")) {
				say("You could try counting on your fingers.");
			} else say("I am sorry, I didn't get that.");
		} else {
			guesses++;
			if (g.getGrammar(answer) == number) {			//a number was typed
				if (guesses == 1) {
					say("That was correct, you find it on the first try.");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);
				} else {
					say("Great! You've found it this time.");
					
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			} else if (g.getGrammar(answer) == (number + 1) || g.getGrammar(answer) == (number - 1) )
				say("You're almost correct! Let's try one more time.");
		    else if(g.getGrammar(answer) > number)
				say("That was too high, think again.");
			else say("That was too low, think again.");
			
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
