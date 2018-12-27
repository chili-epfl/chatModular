package iristk.app.tutoring;

import iristk.system.Event;

import iristk.system.InitializationException;
import iristk.system.IrisModule;

/*SAY AND LISTEN AND COMMENTARY OF SEND*/
/**
 * Creates the MainModule that manages the events relative to the initialization
 * and stop of the system and the comparison and search of the best answer to
 * display
 */
public class MainModule extends IrisModule {

	private String tutoResponse = "";
	private String question = "";
	private Boolean tuto_synchro = false;
	private Boolean question_synchro = false;

	/**
	 * Reacts a certain way according to the events we receive
	 */
	@Override
	public void onEvent(Event event) {
		if (event.triggers("monitor.system.start")) {
			say_and_listen("Could you compute how much is 5 + 5");
		} else if (event.getName().equals("action.stop")) {
			say_and_listen("Bye!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		} else if (event.getName().equals("sense.tuto_response_ready")) {
			/* Reacts to the event that the tutoring module sent a response */
			if (event.has("text")) {
				tutoResponse = event.getString("text");
				tuto_synchro = true;
				send();
			}
		} else if (event.getName().equals("sense.user.type") || event.getName().equals("sense.speech.rec")) {
			/* Reacts to the event that the user has typed or said a question */
			if (event.has("text")) {
				if (event.has("text")) {
					question = event.getString("text");
					question_synchro = true;
				}
			}
		} else if (event.getName().equals("sense.receive")) {
			 /* Reacts to the event that we received an answer from RabbitMQ to be displayed */
			if (event.has("text")) {
				if (event.getString("text").equals("stop")) {
					Event newEvent = new Event("action.stop");
					send(newEvent);
				} else
					say_and_listen(event.getString("text"));
			}
		}
	}

	/**
	 * Sends a message with RabbitMQ containing the question and the tutoring
	 * module's response
	 * 		=> 	Change here if you want to compare the answers of multiple modules
	 * 			mq.publish("test-exchange", "from_server", question + "=====" + response1 + "=====" + response2 + "=====" + ... + "=====" + responseN);
	 */
	private void send() {
		if (tuto_synchro && question_synchro) {
			try {
				MessageQueue mq = new MessageQueue();
				mq.publish("test-exchange", "from_server", question + "=====" + tutoResponse);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		tuto_synchro = false;
		question_synchro = false;
	}

	/**
	 * Sends an event that the speech synthesizer can say the text and another event
	 * that the speech recognizer can listen to the user speak
	 * 
	 * @param text: the text to be put in the event
	 */
	private void say_and_listen(String text) {
		Event newEvent = new Event("action.speech");
		newEvent.put("text", text);
		send(newEvent);

		Event newListenEvent = new Event("action.listen");
		newListenEvent.put("endSilTimeout", 1000);
		newListenEvent.put("noSpeechTimeout", 8000);
		send(newListenEvent);
	}

	@Override
	public void init() throws InitializationException {
		// Initialize the module
	}

}
