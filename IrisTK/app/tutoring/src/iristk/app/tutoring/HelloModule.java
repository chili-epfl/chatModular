package iristk.app.tutoring;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import iristk.system.Event;
import iristk.system.InitializationException;
import iristk.system.IrisModule;
import iristk.util.Record;

public class HelloModule extends IrisModule {
	private JTextField textInput;
    
    public HelloModule() {
        // Specify which events to subscribe to. You don't need to do this, 
        // but it can improve performance when distributing the system across processes.  
        subscribe("action.hello");
        
        /*textInput = new JTextField();
        textInput.setEditable(true);
        textInput.addKeyListener(new KeyListener(){
        	 public void keyPressed(KeyEvent key) {
        		 if (key.getKeyCode() == 10) {
        			 	Event newEventType = new Event("action.typed");
        			 	newEventType.put("text", textInput.getText());
        			 	send(newEventType);
        			 	System.out.println("Send " + textInput.getText());
        		 }
             }

			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
        });
        
        Event newEventHello = new Event("action.hello");
        newEventHello.put("text", "HI");
        send(newEventHello);*/
       
    }   
        
    
    @Override
    public void onEvent(Event event) {
        // We have received an event from some other module, 
        // check if we should react to it
    	Record r = new Record();
    	
    	//System.out.println(r.get("text"));
    	System.out.println(event.getName());
        if (event.getName().equals("action.hello")) {
            // Do the hello here together with the parameter "text"
            System.out.println("Hello: " + event.get("text"));
            // Send a monitor event in response
            Event newEvent = new Event("monitor.hello");
            // Add the parameter text, if anyone should be interested
            newEvent.put("text", event.get("text"));
            send(newEvent);
        }else if (event.getName().equals("action.typed")) {
        	System.out.println("HERE");
        	 System.out.println("Hello: " + event.get("text"));
        }
    }

    @Override
    public void init() throws InitializationException {
        // Initialize the module
    }

}