package iristk.app.tutoring;

import iristk.system.Event;
import iristk.system.InitializationException;
import iristk.system.IrisModule;

public class TutoringModule extends IrisModule {
	
	private Integer number;
	private Integer guesses;
	private Grammar g = new Grammar();
	private String keep_answer;			//Keep last answer typed in
	

	@Override
	public void onEvent(Event event) {
        if (event.triggers("monitor.system.start")) {
        	number = 10;
        	guesses = 0;
        	say("Could you compute how much is 5 + 5", false);
        	//Event newEvent = new Event("action.waitForSpeech");
        }else if (event.getName().equals("sense.user.type")) {
        	/*Check what the user typed*/
        	if(event.has("text")) {
        		keep_answer = event.getString("text");
        		check(event.getString("text"));
        	}
        }else if (event.getName().equals("sense.user.receive")) {
        	/*Check what the forum says and decides if it's relevant or not*/
        	if(event.has("text")) {
        		String text = event.getString("text");
        		if (text.contains(keep_answer)) say("Your answer may be relevant", true);
        		else say("I don't think that your answer is really relevant", true);
        	}
        }
    }
	
	
	private void check(String answer) {
		if (g.getGrammar(answer) == null) {
			if(answer.contains("help") || answer.contains("don't know")) {
				String matching_response = g.getRandomResponse();
				say(matching_response, true);
				//say("You could try counting on your fingers.", true);
			} else say("I am sorry, I didn't get that.", true);
		} else {
			guesses++;
			if (g.getGrammar(answer) == number) {			//a number was typed
				if (guesses == 1) {
					say("That was correct, you find it on the first try.", true);
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);
				} else {
					say("Great! You've found it this time.", true);
					
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			} else if (g.getGrammar(answer) == (number + 1) || g.getGrammar(answer) == (number - 1) )
				say("You're almost correct! Let's try one more time.", true);
		    else if(g.getGrammar(answer) > number)
				say("That was too high, think again.", true);
			else say("That was too low, think again.", true);
			
		}
	}
	
	//We can send either an answer or an information from the system
	private void say(String text, boolean isAnswer) {
		Event newEvent = new Event("action.speech");
		if (isAnswer) newEvent.put("text_answer", text);
		else newEvent.put("text", text);
		send(newEvent);
	}
	

    @Override
    public void init() throws InitializationException {
        // Initialize the module
    }
	
}
