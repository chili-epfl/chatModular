package iristk.app.tutoring;

import java.util.Formatter;

import iristk.system.Event;
import iristk.system.InitializationException;
import iristk.system.IrisModule;

/**
 * Creates the TutoringModule that manages the events relative to the tutoring
 * example
 */
public class TutoringModule extends IrisModule {

	private Integer number;
	private Integer guesses;
	private Grammar g = new Grammar();

	/**
	 * Reacts a certain way according to the events we receive
	 */
	@Override
	public void onEvent(Event event) {
		if (event.triggers("monitor.system.start")) {
			/* Initializes the number to find and the number of guesses */
			number = 10;
			guesses = 0;
		} else if (event.getName().equals("sense.speech.rec") || event.getName().equals("sense.user.type")) {
			/* Checks what the user typed or said */
			if (event.has("text")) {
				check(event.getString("text"));
			}
		}
	}

	/**
	 * Checks what the user typed or said and finds the appropriate response.
	 * 
	 * @param answer: the text to be checked
	 */
	private void check(String answer) {
		StringBuilder sbuf = new StringBuilder();
		Formatter fmt = new Formatter(sbuf);

		if (g.getGrammar(answer) == null) {
			/* A sentence was typed */
			if (answer.contains("help") || answer.contains("don't know")) {
				/* The prefix is needed to be able to do answers comparison with word2vec */
				String prefix = "";
				if (answer.contains("help"))
					prefix = "You need some help?";
				else if (answer.contains("don't know"))
					prefix = "You don't know?";
				String matching_response = g.getRandomResponse();
				fmt.format("%s %s", prefix, matching_response);
			} else
				send_tuto_response("I am sorry, I didn't get that.");
		} else {
			/* A number was typed */
			guesses++;
			if (g.getGrammar(answer) == number) {
				if (guesses == 1) {
					fmt.format("%s is correct, you find it on the first try", answer);
				} else {
					fmt.format("%s is correct. Great! You've found it this time.", answer);
				}
			} else if (g.getGrammar(answer) == (number + 1) || g.getGrammar(answer) == (number - 1)) {
				fmt.format("%s is almost correct! Let's try one more time.", answer);
			} else if (g.getGrammar(answer) > number) {
				fmt.format("%s is too high, think again.", answer);
			} else {
				fmt.format("%s is too low, think again.", answer);
			}
		}
		send_tuto_response(sbuf.toString());
		fmt.close();
	}

	/**
	 * Sends an event that the tutoring module response is ready, containing the
	 * response of this module
	 * 
	 * @param text: the text to be put in the event
	 */
	private void send_tuto_response(String text) {
		Event newEvent = new Event("action.tuto_response_ready");
		newEvent.put("text", text);
		send(newEvent);
	}

	@Override
	public void init() throws InitializationException {
		// Initialize the module
	}

}
