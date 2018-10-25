/*******************************************************************************
 * Copyright (c) 2014 Gabriel Skantze.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Gabriel Skantze - initial API and implementation
 ******************************************************************************/
package iristk.app.tutoring;

import iristk.speech.SpeechGrammarContext;
import iristk.speech.Voice.Gender;
import iristk.speech.windows.WindowsRecognizerFactory;
import iristk.speech.windows.WindowsSynthesizer;
import iristk.system.IrisUtils;
import iristk.system.SimpleDialogSystem;
import iristk.util.Language;

import java.io.File;
import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import iristk.cfg.SRGSGrammar;
import iristk.flow.FlowModule;

public class TutoringSystem {

	public TutoringSystem() throws Exception {
		// Create the system
		SimpleDialogSystem system = new SimpleDialogSystem(this.getClass());
		
		// Set the language of the system
		system.setLanguage(Language.ENGLISH_US);
		
		// Uncomment this if you want to turn on logging
		//system.setupLogging(new File("c:/Users/Meret/IrisTK/app/tutoring/src/iristk/app/tutoring"), true);
		
		// Set up the GUI
		system.setupGUI();
		
		// Add the recognizer to the system
		system.setupRecognizer(new WindowsRecognizerFactory());
		//system.setupConsoleRecognizer();
		system.getRecognizerModule().setPartialResults(true);
		
		// Add a synthesizer to the system		
		system.setupSynthesizer(new WindowsSynthesizer(), Gender.FEMALE);
		
		// Add the flow
		//system.addModule(new FlowModule(new TutoringFlow()));
		system.addModule(new MessageQueue());
		system.addModule(new NewModule());
		//system.addModule(new HelloModule());
		
		// Load a grammar in the recognizer
		system.loadContext("default", new SpeechGrammarContext(new SRGSGrammar(system.getPackageFile("TutoringGrammar.xml"))));
		
		// Start the interaction
		system.sendStartSignal();
		
	}

	public static void main(String[] args) throws Exception {
		
		//Producer_ p = new Producer_();
		//p.serverPublish("Connected!");
		
		//Consumer_ m = new Consumer_();
		//m.serverConsume();
		//m.clientConsume();
		
		MessageQueue mq = new MessageQueue();
		mq.bindQueue("test-exchange", "from_client");
		mq.consume();
		
		new TutoringSystem();
		
	}


}
