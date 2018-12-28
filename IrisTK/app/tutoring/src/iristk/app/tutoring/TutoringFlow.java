package iristk.app.tutoring;

import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import static iristk.util.Converters.*;

/**
 * Class generated automatically from TutoringFlow.xml
 */
public class TutoringFlow extends iristk.flow.Flow {

	private Integer number;
	private Integer guesses;
	private Grammar g;

	private void initVariables() {
		g = (Grammar) new Grammar();
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer value) {
		this.number = value;
	}

	public Integer getGuesses() {
		return this.guesses;
	}

	public void setGuesses(Integer value) {
		this.guesses = value;
	}

	public Grammar getG() {
		return this.g;
	}

	public void setG(Grammar value) {
		this.g = value;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("number")) return this.number;
		if (name.equals("guesses")) return this.guesses;
		if (name.equals("g")) return this.g;
		return null;
	}


	public TutoringFlow() {
		initVariables();
	}

	@Override
	public State getInitialState() {return new Start();}


	public class Start extends State implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 12
			try {
				EXECUTION: {
					int count = getCount(1588970020) + 1;
					incrCount(1588970020);
					// Line: 13
					number = 10;
					// Line: 14
					guesses = 0;
					iristk.flow.DialogFlow.say state0 = new iristk.flow.DialogFlow.say();
					StringCreator string1 = new StringCreator();
					string1.append("Could you compute how much is 5 + 5?");
					state0.setText(string1.toString());
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 12, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 16
					Guess state2 = new Guess();
					flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 16, 25)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 12, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Guess extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 21
			try {
				EXECUTION: {
					int count = getCount(183264084) + 1;
					incrCount(183264084);
					iristk.flow.DialogFlow.listen state3 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 21, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 21, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 25
			try {
				count = getCount(476402209) + 1;
				if (event.triggers("sense.user.type")) {
					if (g.getGrammar((String)event.get("text")) != null) {
						incrCount(476402209);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 26
							guesses++;
							// Line: 27
							if (g.getGrammar((String)event.get("text")) == number) {
								// Line: 28
								if (guesses == 1) {
									iristk.flow.DialogFlow.say state4 = new iristk.flow.DialogFlow.say();
									StringCreator string5 = new StringCreator();
									string5.append("That was correct, you find it on the first try.");
									state4.setText(string5.toString());
									if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 28, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 30
								} else {
									iristk.flow.DialogFlow.say state6 = new iristk.flow.DialogFlow.say();
									StringCreator string7 = new StringCreator();
									string7.append("Great! You've found it this time.");
									state6.setText(string7.toString());
									if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 28, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
								// Line: 33
								CheckAgain state8 = new CheckAgain();
								flowThread.gotoState(state8, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 33, 31)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 34
							} else if (g.getGrammar((String)event.get("text")) == (number - 1) || g.getGrammar((String)event.get("text")) == (number + 1)) {
								iristk.flow.DialogFlow.say state9 = new iristk.flow.DialogFlow.say();
								StringCreator string10 = new StringCreator();
								string10.append("You're almost correct! Let's try one more time.");
								state9.setText(string10.toString());
								if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 27, 58)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 36
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 36, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 37
							} else if (g.getGrammar((String)event.get("text")) > number) {
								// Line: 38
								boolean chosen11 = false;
								boolean matching12 = true;
								while (!chosen11 && matching12) {
									int rand13 = random(110718392, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching12 = false;
									if (true) {
										matching12 = true;
										if (rand13 >= 0 && rand13 < 1) {
											chosen11 = true;
											iristk.flow.DialogFlow.say state14 = new iristk.flow.DialogFlow.say();
											StringCreator string15 = new StringCreator();
											string15.append("That was too high, think again.");
											state14.setText(string15.toString());
											if (!flowThread.callState(state14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 38, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching12 = true;
										if (rand13 >= 1 && rand13 < 2) {
											chosen11 = true;
											iristk.flow.DialogFlow.say state16 = new iristk.flow.DialogFlow.say();
											StringCreator string17 = new StringCreator();
											string17.append("That's still not quite it, let's think again.");
											state16.setText(string17.toString());
											if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 38, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 42
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 42, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 43
							} else {
								// Line: 44
								boolean chosen18 = false;
								boolean matching19 = true;
								while (!chosen18 && matching19) {
									int rand20 = random(1100439041, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching19 = false;
									if (true) {
										matching19 = true;
										if (rand20 >= 0 && rand20 < 1) {
											chosen18 = true;
											iristk.flow.DialogFlow.say state21 = new iristk.flow.DialogFlow.say();
											StringCreator string22 = new StringCreator();
											string22.append("That was too low, think again.");
											state21.setText(string22.toString());
											if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 44, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching19 = true;
										if (rand20 >= 1 && rand20 < 2) {
											chosen18 = true;
											iristk.flow.DialogFlow.say state23 = new iristk.flow.DialogFlow.say();
											StringCreator string24 = new StringCreator();
											string24.append("I'm sure you can find it, let's try again.");
											state23.setText(string24.toString());
											if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 44, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 48
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 48, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 25, 83));
			}
			// Line: 52
			try {
				count = getCount(231685785) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:number")) {
						incrCount(231685785);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 53
							guesses++;
							// Line: 54
							if (asInteger(event.get("sem:number")) == number) {
								// Line: 55
								if (guesses == 1) {
									iristk.flow.DialogFlow.say state25 = new iristk.flow.DialogFlow.say();
									StringCreator string26 = new StringCreator();
									string26.append("That was correct, you find it on the first try.");
									state25.setText(string26.toString());
									if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 55, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 57
								} else {
									iristk.flow.DialogFlow.say state27 = new iristk.flow.DialogFlow.say();
									StringCreator string28 = new StringCreator();
									string28.append("Great! You've found it this time.");
									state27.setText(string28.toString());
									if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 55, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
								// Line: 60
								CheckAgain state29 = new CheckAgain();
								flowThread.gotoState(state29, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 60, 31)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 61
							} else if (asInteger(event.get("sem:number")) == 9 || asInteger(event.get("sem:number")) == 11) {
								iristk.flow.DialogFlow.say state30 = new iristk.flow.DialogFlow.say();
								StringCreator string31 = new StringCreator();
								string31.append("You're almost correct! Let's try one more time.");
								state30.setText(string31.toString());
								if (!flowThread.callState(state30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 54, 53)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 63
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 63, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 64
							} else if (asInteger(event.get("sem:number")) > number) {
								// Line: 65
								boolean chosen32 = false;
								boolean matching33 = true;
								while (!chosen32 && matching33) {
									int rand34 = random(1365202186, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching33 = false;
									if (true) {
										matching33 = true;
										if (rand34 >= 0 && rand34 < 1) {
											chosen32 = true;
											iristk.flow.DialogFlow.say state35 = new iristk.flow.DialogFlow.say();
											StringCreator string36 = new StringCreator();
											string36.append("That was too high, think again.");
											state35.setText(string36.toString());
											if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 65, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching33 = true;
										if (rand34 >= 1 && rand34 < 2) {
											chosen32 = true;
											iristk.flow.DialogFlow.say state37 = new iristk.flow.DialogFlow.say();
											StringCreator string38 = new StringCreator();
											string38.append("That's still not quite it, let's think again.");
											state37.setText(string38.toString());
											if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 65, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 69
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 69, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 70
							} else {
								// Line: 71
								boolean chosen39 = false;
								boolean matching40 = true;
								while (!chosen39 && matching40) {
									int rand41 = random(932583850, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching40 = false;
									if (true) {
										matching40 = true;
										if (rand41 >= 0 && rand41 < 1) {
											chosen39 = true;
											iristk.flow.DialogFlow.say state42 = new iristk.flow.DialogFlow.say();
											StringCreator string43 = new StringCreator();
											string43.append("That was too low, think again.");
											state42.setText(string43.toString());
											if (!flowThread.callState(state42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 71, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching40 = true;
										if (rand41 >= 1 && rand41 < 2) {
											chosen39 = true;
											iristk.flow.DialogFlow.say state44 = new iristk.flow.DialogFlow.say();
											StringCreator string45 = new StringCreator();
											string45.append("I'm sure you can find it, let's try again.");
											state44.setText(string45.toString());
											if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 71, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 75
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 75, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 52, 61));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class CheckAgain extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 81
			try {
				EXECUTION: {
					int count = getCount(305808283) + 1;
					incrCount(305808283);
					iristk.flow.DialogFlow.say state46 = new iristk.flow.DialogFlow.say();
					StringCreator string47 = new StringCreator();
					string47.append("Do you want to try again?");
					state46.setText(string47.toString());
					if (!flowThread.callState(state46, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 81, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state48 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state48, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 81, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 81, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 85
			try {
				count = getCount(2111991224) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.get("text").equals("yes")) {
						incrCount(2111991224);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state49 = new iristk.flow.DialogFlow.say();
							StringCreator string50 = new StringCreator();
							string50.append("Okay, let's try again.");
							state49.setText(string50.toString());
							if (!flowThread.callState(state49, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 85, 65)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 87
							Start state51 = new Start();
							flowThread.gotoState(state51, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 87, 25)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 85, 65));
			}
			// Line: 89
			try {
				count = getCount(917142466) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(917142466);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state52 = new iristk.flow.DialogFlow.say();
							StringCreator string53 = new StringCreator();
							string53.append("Okay, let's try again.");
							state52.setText(string53.toString());
							if (!flowThread.callState(state52, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 89, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 91
							Start state54 = new Start();
							flowThread.gotoState(state54, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 91, 25)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 89, 58));
			}
			// Line: 93
			try {
				count = getCount(405662939) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.get("text").equals("no")) {
						incrCount(405662939);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state55 = new iristk.flow.DialogFlow.say();
							StringCreator string56 = new StringCreator();
							string56.append("Okay, I was glad to help you.");
							state55.setText(string56.toString());
							if (!flowThread.callState(state55, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 93, 64)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 95
							System.exit(0);
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 93, 64));
			}
			// Line: 97
			try {
				count = getCount(1130478920) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(1130478920);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state57 = new iristk.flow.DialogFlow.say();
							StringCreator string58 = new StringCreator();
							string58.append("Okay, I was glad to help you.");
							state57.setText(string58.toString());
							if (!flowThread.callState(state57, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 97, 57)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 99
							System.exit(0);
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 97, 57));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Dialog extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 104
			try {
				count = getCount(123961122) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.getString("text").contains("help")) {
						incrCount(123961122);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 105
							boolean chosen59 = false;
							boolean matching60 = true;
							while (!chosen59 && matching60) {
								int rand61 = random(1227229563, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching60 = false;
								if (true) {
									matching60 = true;
									if (rand61 >= 0 && rand61 < 1) {
										chosen59 = true;
										iristk.flow.DialogFlow.say state62 = new iristk.flow.DialogFlow.say();
										StringCreator string63 = new StringCreator();
										string63.append("You could try counting on your fingers.");
										state62.setText(string63.toString());
										if (!flowThread.callState(state62, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 105, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching60 = true;
									if (rand61 >= 1 && rand61 < 2) {
										chosen59 = true;
										iristk.flow.DialogFlow.say state64 = new iristk.flow.DialogFlow.say();
										StringCreator string65 = new StringCreator();
										string65.append("Try to see this problem as if you had to sum 5 apples and 5 bananas.");
										state64.setText(string65.toString());
										if (!flowThread.callState(state64, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 105, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching60 = true;
									if (rand61 >= 2 && rand61 < 3) {
										chosen59 = true;
										iristk.flow.DialogFlow.say state66 = new iristk.flow.DialogFlow.say();
										StringCreator string67 = new StringCreator();
										string67.append("Think again, I'm sure you can find it!");
										state66.setText(string67.toString());
										if (!flowThread.callState(state66, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 105, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 110
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 110, 23)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 104, 80));
			}
			// Line: 113
			try {
				count = getCount(1562557367) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:dontknow")) {
						incrCount(1562557367);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 114
							boolean chosen68 = false;
							boolean matching69 = true;
							while (!chosen68 && matching69) {
								int rand70 = random(1101288798, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching69 = false;
								if (true) {
									matching69 = true;
									if (rand70 >= 0 && rand70 < 1) {
										chosen68 = true;
										iristk.flow.DialogFlow.say state71 = new iristk.flow.DialogFlow.say();
										StringCreator string72 = new StringCreator();
										string72.append("You could try counting on your fingers.");
										state71.setText(string72.toString());
										if (!flowThread.callState(state71, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 114, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching69 = true;
									if (rand70 >= 1 && rand70 < 2) {
										chosen68 = true;
										iristk.flow.DialogFlow.say state73 = new iristk.flow.DialogFlow.say();
										StringCreator string74 = new StringCreator();
										string74.append("Try to see this problem as if you had to sum 5 apples and 5 bananas.");
										state73.setText(string74.toString());
										if (!flowThread.callState(state73, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 114, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching69 = true;
									if (rand70 >= 2 && rand70 < 3) {
										chosen68 = true;
										iristk.flow.DialogFlow.say state75 = new iristk.flow.DialogFlow.say();
										StringCreator string76 = new StringCreator();
										string76.append("Think again, I'm sure you can find it!");
										state75.setText(string76.toString());
										if (!flowThread.callState(state75, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 114, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 119
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 119, 23)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 113, 70));
			}
			// Line: 122
			try {
				count = getCount(971848845) + 1;
				if (event.triggers("sense.user.type")) {
					incrCount(971848845);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state77 = new iristk.flow.DialogFlow.say();
						StringCreator string78 = new StringCreator();
						string78.append("I am sorry, I didn't get that.");
						state77.setText(string78.toString());
						if (!flowThread.callState(state77, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 122, 35)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 124
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 124, 14)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 122, 35));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
