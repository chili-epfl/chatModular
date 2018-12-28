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
			// Line: 11
			try {
				EXECUTION: {
					int count = getCount(1607460018) + 1;
					incrCount(1607460018);
					// Line: 12
					number = 10;
					// Line: 13
					guesses = 0;
					iristk.flow.DialogFlow.say state0 = new iristk.flow.DialogFlow.say();
					StringCreator string1 = new StringCreator();
					string1.append("Could you compute how much is 5 + 5?");
					state0.setText(string1.toString());
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 11, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 15
					Guess state2 = new Guess();
					flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 15, 25)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 11, 12));
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
			// Line: 20
			try {
				EXECUTION: {
					int count = getCount(2121744517) + 1;
					incrCount(2121744517);
					iristk.flow.DialogFlow.listen state3 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 20, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 20, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 24
			try {
				count = getCount(1066376662) + 1;
				if (event.triggers("sense.user.type")) {
					if (g.getGrammar((String)event.get("text")) != null) {
						incrCount(1066376662);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 25
							guesses++;
							// Line: 26
							if (g.getGrammar((String)event.get("text")) == number) {
								// Line: 27
								if (guesses == 1) {
									iristk.flow.DialogFlow.say state4 = new iristk.flow.DialogFlow.say();
									StringCreator string5 = new StringCreator();
									string5.append("That was correct, you find it on the first try.");
									state4.setText(string5.toString());
									if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 27, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 29
								} else {
									iristk.flow.DialogFlow.say state6 = new iristk.flow.DialogFlow.say();
									StringCreator string7 = new StringCreator();
									string7.append("Great! You've found it this time.");
									state6.setText(string7.toString());
									if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 27, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
								// Line: 32
								CheckAgain state8 = new CheckAgain();
								flowThread.gotoState(state8, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 32, 31)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 33
							} else if (g.getGrammar((String)event.get("text")) == (number - 1) || g.getGrammar((String)event.get("text")) == (number + 1)) {
								iristk.flow.DialogFlow.say state9 = new iristk.flow.DialogFlow.say();
								StringCreator string10 = new StringCreator();
								string10.append("You're almost correct! Let's try one more time.");
								state9.setText(string10.toString());
								if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 26, 58)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 35
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 35, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 36
							} else if (g.getGrammar((String)event.get("text")) > number) {
								// Line: 37
								boolean chosen11 = false;
								boolean matching12 = true;
								while (!chosen11 && matching12) {
									int rand13 = random(517938326, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching12 = false;
									if (true) {
										matching12 = true;
										if (rand13 >= 0 && rand13 < 1) {
											chosen11 = true;
											iristk.flow.DialogFlow.say state14 = new iristk.flow.DialogFlow.say();
											StringCreator string15 = new StringCreator();
											string15.append("That was too high, think again.");
											state14.setText(string15.toString());
											if (!flowThread.callState(state14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 37, 13)))) {
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
											if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 37, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 41
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 41, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 42
							} else {
								// Line: 43
								boolean chosen18 = false;
								boolean matching19 = true;
								while (!chosen18 && matching19) {
									int rand20 = random(425918570, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching19 = false;
									if (true) {
										matching19 = true;
										if (rand20 >= 0 && rand20 < 1) {
											chosen18 = true;
											iristk.flow.DialogFlow.say state21 = new iristk.flow.DialogFlow.say();
											StringCreator string22 = new StringCreator();
											string22.append("That was too low, think again.");
											state21.setText(string22.toString());
											if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 43, 13)))) {
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
											if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 43, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 47
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 47, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 24, 83));
			}
			// Line: 51
			try {
				count = getCount(1100439041) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:number")) {
						incrCount(1100439041);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 52
							guesses++;
							// Line: 53
							if (asInteger(event.get("sem:number")) == number) {
								// Line: 54
								if (guesses == 1) {
									iristk.flow.DialogFlow.say state25 = new iristk.flow.DialogFlow.say();
									StringCreator string26 = new StringCreator();
									string26.append("That was correct, you find it on the first try.");
									state25.setText(string26.toString());
									if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 54, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 56
								} else {
									iristk.flow.DialogFlow.say state27 = new iristk.flow.DialogFlow.say();
									StringCreator string28 = new StringCreator();
									string28.append("Great! You've found it this time.");
									state27.setText(string28.toString());
									if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 54, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
								// Line: 59
								CheckAgain state29 = new CheckAgain();
								flowThread.gotoState(state29, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 59, 31)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 60
							} else if (asInteger(event.get("sem:number")) == (number - 1) || asInteger(event.get("sem:number")) == (number + 1)) {
								iristk.flow.DialogFlow.say state30 = new iristk.flow.DialogFlow.say();
								StringCreator string31 = new StringCreator();
								string31.append("You're almost correct! Let's try one more time.");
								state30.setText(string31.toString());
								if (!flowThread.callState(state30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 53, 53)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 62
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 62, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 63
							} else if (asInteger(event.get("sem:number")) > number) {
								// Line: 64
								boolean chosen32 = false;
								boolean matching33 = true;
								while (!chosen32 && matching33) {
									int rand34 = random(515132998, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching33 = false;
									if (true) {
										matching33 = true;
										if (rand34 >= 0 && rand34 < 1) {
											chosen32 = true;
											iristk.flow.DialogFlow.say state35 = new iristk.flow.DialogFlow.say();
											StringCreator string36 = new StringCreator();
											string36.append("That was too high, think again.");
											state35.setText(string36.toString());
											if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 64, 13)))) {
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
											if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 64, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 68
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 68, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 69
							} else {
								// Line: 70
								boolean chosen39 = false;
								boolean matching40 = true;
								while (!chosen39 && matching40) {
									int rand41 = random(1651191114, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching40 = false;
									if (true) {
										matching40 = true;
										if (rand41 >= 0 && rand41 < 1) {
											chosen39 = true;
											iristk.flow.DialogFlow.say state42 = new iristk.flow.DialogFlow.say();
											StringCreator string43 = new StringCreator();
											string43.append("That was too low, think again.");
											state42.setText(string43.toString());
											if (!flowThread.callState(state42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 70, 13)))) {
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
											if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 70, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 74
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 74, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 51, 61));
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
			// Line: 80
			try {
				EXECUTION: {
					int count = getCount(1579572132) + 1;
					incrCount(1579572132);
					iristk.flow.DialogFlow.say state46 = new iristk.flow.DialogFlow.say();
					StringCreator string47 = new StringCreator();
					string47.append("Do you want to try again?");
					state46.setText(string47.toString());
					if (!flowThread.callState(state46, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 80, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state48 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state48, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 80, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 80, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 84
			try {
				count = getCount(359023572) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.getString("text").equals("yes")) {
						incrCount(359023572);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state49 = new iristk.flow.DialogFlow.say();
							StringCreator string50 = new StringCreator();
							string50.append("Okay, let's try again.");
							state49.setText(string50.toString());
							if (!flowThread.callState(state49, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 84, 80)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 86
							Start state51 = new Start();
							flowThread.gotoState(state51, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 86, 25)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 84, 80));
			}
			// Line: 88
			try {
				count = getCount(2111991224) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(2111991224);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state52 = new iristk.flow.DialogFlow.say();
							StringCreator string53 = new StringCreator();
							string53.append("Okay, let's try again.");
							state52.setText(string53.toString());
							if (!flowThread.callState(state52, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 88, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 90
							Start state54 = new Start();
							flowThread.gotoState(state54, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 90, 25)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 88, 58));
			}
			// Line: 92
			try {
				count = getCount(917142466) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.getString("text").equals("no")) {
						incrCount(917142466);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state55 = new iristk.flow.DialogFlow.say();
							StringCreator string56 = new StringCreator();
							string56.append("Okay, I was glad to help you.");
							state55.setText(string56.toString());
							if (!flowThread.callState(state55, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 92, 79)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 94
							System.exit(0);
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 92, 79));
			}
			// Line: 96
			try {
				count = getCount(405662939) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(405662939);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state57 = new iristk.flow.DialogFlow.say();
							StringCreator string58 = new StringCreator();
							string58.append("Okay, I was glad to help you.");
							state57.setText(string58.toString());
							if (!flowThread.callState(state57, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 96, 57)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 98
							System.exit(0);
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 96, 57));
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
			// Line: 103
			try {
				count = getCount(1404928347) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.getString("text").contains("help")) {
						incrCount(1404928347);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 104
							boolean chosen59 = false;
							boolean matching60 = true;
							while (!chosen59 && matching60) {
								int rand61 = random(604107971, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching60 = false;
								if (true) {
									matching60 = true;
									if (rand61 >= 0 && rand61 < 1) {
										chosen59 = true;
										iristk.flow.DialogFlow.say state62 = new iristk.flow.DialogFlow.say();
										StringCreator string63 = new StringCreator();
										string63.append("You could try counting on your fingers.");
										state62.setText(string63.toString());
										if (!flowThread.callState(state62, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 104, 13)))) {
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
										if (!flowThread.callState(state64, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 104, 13)))) {
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
										if (!flowThread.callState(state66, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 104, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 109
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 109, 23)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 103, 84));
			}
			// Line: 112
			try {
				count = getCount(1227229563) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:dontknow")) {
						incrCount(1227229563);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 113
							boolean chosen68 = false;
							boolean matching69 = true;
							while (!chosen68 && matching69) {
								int rand70 = random(1982791261, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching69 = false;
								if (true) {
									matching69 = true;
									if (rand70 >= 0 && rand70 < 1) {
										chosen68 = true;
										iristk.flow.DialogFlow.say state71 = new iristk.flow.DialogFlow.say();
										StringCreator string72 = new StringCreator();
										string72.append("You could try counting on your fingers.");
										state71.setText(string72.toString());
										if (!flowThread.callState(state71, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 113, 13)))) {
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
										if (!flowThread.callState(state73, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 113, 13)))) {
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
										if (!flowThread.callState(state75, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 113, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 118
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 118, 23)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 112, 70));
			}
			// Line: 121
			try {
				count = getCount(1101288798) + 1;
				if (event.triggers("sense.user.type")) {
					incrCount(1101288798);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state77 = new iristk.flow.DialogFlow.say();
						StringCreator string78 = new StringCreator();
						string78.append("I am sorry, I didn't get that.");
						state77.setText(string78.toString());
						if (!flowThread.callState(state77, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 121, 43)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 123
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 123, 14)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 121, 43));
			}
			// Line: 126
			try {
				count = getCount(971848845) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(971848845);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state79 = new iristk.flow.DialogFlow.say();
						StringCreator string80 = new StringCreator();
						string80.append("I am sorry, I didn't hear anything.");
						state79.setText(string80.toString());
						if (!flowThread.callState(state79, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 126, 44)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 128
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 128, 14)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\Documents\\EPFL\\3Annee\\Semestre_5\\Projet\\GitHubDepository\\chatModular\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 126, 44));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
