package iristk.app.tutoring;

import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;

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
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 12, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 17
					Guess state2 = new Guess();
					flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 17, 25)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 12, 12));
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
			// Line: 22
			try {
				EXECUTION: {
					int count = getCount(476402209) + 1;
					incrCount(476402209);
					// Line: 23
					//send(new Event("action.waitForSpeech"));
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 22, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 26
			try {
				count = getCount(460332449) + 1;
				if (event.triggers("sense.user.type")) {
					if (g.getGrammar((String)event.get("text")) != null) {
						incrCount(460332449);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 27
							guesses++;
							// Line: 28
							if (g.getGrammar((String)event.get("text")) == number) {
								// Line: 29
								if (guesses == 1) {
									iristk.flow.DialogFlow.say state3 = new iristk.flow.DialogFlow.say();
									StringCreator string4 = new StringCreator();
									string4.append("That was correct, you find it on the first try.");
									state3.setText(string4.toString());
									if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 29, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 31
								} else {
									iristk.flow.DialogFlow.say state5 = new iristk.flow.DialogFlow.say();
									StringCreator string6 = new StringCreator();
									string6.append("Great! You've found it this time.");
									state5.setText(string6.toString());
									if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 29, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
								// Line: 34
								CheckAgain state7 = new CheckAgain();
								flowThread.gotoState(state7, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 34, 31)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 35
							} else if (g.getGrammar((String)event.get("text")) == (number - 1) || g.getGrammar((String)event.get("text")) == (number + 1)) {
								iristk.flow.DialogFlow.say state8 = new iristk.flow.DialogFlow.say();
								StringCreator string9 = new StringCreator();
								string9.append("You're almost correct! Let's try one more time.");
								state8.setText(string9.toString());
								if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 28, 58)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 37
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 37, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 38
							} else if (g.getGrammar((String)event.get("text")) > number) {
								// Line: 39
								boolean chosen10 = false;
								boolean matching11 = true;
								while (!chosen10 && matching11) {
									int rand12 = random(2143192188, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching11 = false;
									if (true) {
										matching11 = true;
										if (rand12 >= 0 && rand12 < 1) {
											chosen10 = true;
											iristk.flow.DialogFlow.say state13 = new iristk.flow.DialogFlow.say();
											StringCreator string14 = new StringCreator();
											string14.append("That was too high, think again.");
											state13.setText(string14.toString());
											if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 39, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching11 = true;
										if (rand12 >= 1 && rand12 < 2) {
											chosen10 = true;
											iristk.flow.DialogFlow.say state15 = new iristk.flow.DialogFlow.say();
											StringCreator string16 = new StringCreator();
											string16.append("That's still not quite it, let's think again.");
											state15.setText(string16.toString());
											if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 39, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 43
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 43, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 44
							} else {
								// Line: 45
								boolean chosen17 = false;
								boolean matching18 = true;
								while (!chosen17 && matching18) {
									int rand19 = random(231685785, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching18 = false;
									if (true) {
										matching18 = true;
										if (rand19 >= 0 && rand19 < 1) {
											chosen17 = true;
											iristk.flow.DialogFlow.say state20 = new iristk.flow.DialogFlow.say();
											StringCreator string21 = new StringCreator();
											string21.append("That was too low, think again.");
											state20.setText(string21.toString());
											if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 45, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching18 = true;
										if (rand19 >= 1 && rand19 < 2) {
											chosen17 = true;
											iristk.flow.DialogFlow.say state22 = new iristk.flow.DialogFlow.say();
											StringCreator string23 = new StringCreator();
											string23.append("I'm sure you can find it, let's try again.");
											state22.setText(string23.toString());
											if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 45, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 49
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 49, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 26, 83));
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
			// Line: 55
			try {
				EXECUTION: {
					int count = getCount(32374789) + 1;
					incrCount(32374789);
					iristk.flow.DialogFlow.say state24 = new iristk.flow.DialogFlow.say();
					StringCreator string25 = new StringCreator();
					string25.append("Do you want to try again?");
					state24.setText(string25.toString());
					if (!flowThread.callState(state24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 55, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state26 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 55, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 55, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 59
			try {
				count = getCount(1973538135) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.get("text").equals("yes")) {
						incrCount(1973538135);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state27 = new iristk.flow.DialogFlow.say();
							StringCreator string28 = new StringCreator();
							string28.append("Okay, let's try again.");
							state27.setText(string28.toString());
							if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 59, 60)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 61
							Start state29 = new Start();
							flowThread.gotoState(state29, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 61, 25)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 59, 60));
			}
			// Line: 63
			try {
				count = getCount(1865127310) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.get("text").equals("no")) {
						incrCount(1865127310);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state30 = new iristk.flow.DialogFlow.say();
							StringCreator string31 = new StringCreator();
							string31.append("Okay, I was glad to help you.");
							state30.setText(string31.toString());
							if (!flowThread.callState(state30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 63, 61)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 65
							System.exit(0);
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 63, 61));
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
			// Line: 70
			try {
				count = getCount(1651191114) + 1;
				if (event.triggers("sense.user.type")) {
					if (event.getString("text").contains("help")) {
						incrCount(1651191114);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 71
							boolean chosen32 = false;
							boolean matching33 = true;
							while (!chosen32 && matching33) {
								int rand34 = random(1586600255, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching33 = false;
								if (true) {
									matching33 = true;
									if (rand34 >= 0 && rand34 < 1) {
										chosen32 = true;
										iristk.flow.DialogFlow.say state35 = new iristk.flow.DialogFlow.say();
										StringCreator string36 = new StringCreator();
										string36.append("You could try counting on your fingers.");
										state35.setText(string36.toString());
										if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 71, 13)))) {
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
										string38.append("Try to see this problem as if you had to sum 5 apples and 5 bananas.");
										state37.setText(string38.toString());
										if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 71, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 75
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 75, 23)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 70, 80));
			}
			// Line: 78
			try {
				count = getCount(932583850) + 1;
				if (event.triggers("sense.user.type")) {
					incrCount(932583850);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state39 = new iristk.flow.DialogFlow.say();
						StringCreator string40 = new StringCreator();
						string40.append("I am sorry, I didn't get that.");
						state39.setText(string40.toString());
						if (!flowThread.callState(state39, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 78, 35)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 80
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 80, 14)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 78, 35));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
