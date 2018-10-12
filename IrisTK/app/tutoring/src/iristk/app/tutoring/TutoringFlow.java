package iristk.app.tutoring;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.speech.Console;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;

public class TutoringFlow extends iristk.flow.Flow {

	private Integer number;
	private Integer guesses;
	private String answer;

	private void initVariables() {
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

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String value) {
		this.answer = value;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("number")) return this.number;
		if (name.equals("guesses")) return this.guesses;
		if (name.equals("answer")) return this.answer;
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
					int count = getCount(1811075214) + 1;
					incrCount(1811075214);
					// Line: 12
					number = 10;
					// Line: 13
					guesses = 0;
					iristk.flow.DialogFlow.say state0 = new iristk.flow.DialogFlow.say();
					StringCreator string1 = new StringCreator();
					string1.append("Could you compute how much is 5 + 5?");
					state0.setText(string1.toString());
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 11, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 15
					Producer.serverPublish("Could you compute how much is 5 + 5?");
					// Line: 16
					Guess state2 = new Guess();
					flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 16, 25)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 11, 12));
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
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 21, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 21, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 24
			try {
				count = getCount(476402209) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:number")) {
						incrCount(476402209);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 25
							guesses++;
							// Line: 26
							answer = Console.getAnswer();
							// Line: 27
							if (asInteger(answer) == number) {
								// Line: 28
								if (guesses == 1) {
									iristk.flow.DialogFlow.say state4 = new iristk.flow.DialogFlow.say();
									StringCreator string5 = new StringCreator();
									string5.append("That was correct, you find it on the first try.");
									state4.setText(string5.toString());
									if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 28, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 30
									Producer.serverPublish("That was correct, you find it on the first try.");
									// Line: 31
								} else {
									iristk.flow.DialogFlow.say state6 = new iristk.flow.DialogFlow.say();
									StringCreator string7 = new StringCreator();
									string7.append("Great! You've found it this time.");
									state6.setText(string7.toString());
									if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 28, 29)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 33
									Producer.serverPublish("Great! You've found it this time.");
								}
								// Line: 35
								CheckAgain state8 = new CheckAgain();
								flowThread.gotoState(state8, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 35, 31)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 36
							} else if (asInteger(event.get("sem:number")) == 9 || asInteger(event.get("sem:number")) == 11) {
								iristk.flow.DialogFlow.say state9 = new iristk.flow.DialogFlow.say();
								StringCreator string10 = new StringCreator();
								string10.append("You're almost correct! Let's try one more time.");
								state9.setText(string10.toString());
								if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 27, 43)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 38
								Producer.serverPublish("You're almost correct! Let's try one more time.");
								// Line: 39
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 39, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 40
							} else if (asInteger(event.get("sem:number")) > number) {
								// Line: 41
								boolean chosen11 = false;
								boolean matching12 = true;
								while (!chosen11 && matching12) {
									int rand13 = random(204349222, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching12 = false;
									if (true) {
										matching12 = true;
										if (rand13 >= 0 && rand13 < 1) {
											chosen11 = true;
											iristk.flow.DialogFlow.say state14 = new iristk.flow.DialogFlow.say();
											StringCreator string15 = new StringCreator();
											string15.append("That was too high, think again.");
											state14.setText(string15.toString());
											if (!flowThread.callState(state14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 41, 13)))) {
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
											if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 41, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 45
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 45, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 46
							} else {
								// Line: 47
								boolean chosen18 = false;
								boolean matching19 = true;
								while (!chosen18 && matching19) {
									int rand20 = random(2110121908, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching19 = false;
									if (true) {
										matching19 = true;
										if (rand20 >= 0 && rand20 < 1) {
											chosen18 = true;
											iristk.flow.DialogFlow.say state21 = new iristk.flow.DialogFlow.say();
											StringCreator string22 = new StringCreator();
											string22.append("That was too low, think again.");
											state21.setText(string22.toString());
											if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 47, 13)))) {
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
											if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 47, 13)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
								// Line: 51
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 51, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 24, 61));
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
			// Line: 57
			try {
				EXECUTION: {
					int count = getCount(1023487453) + 1;
					incrCount(1023487453);
					iristk.flow.DialogFlow.say state25 = new iristk.flow.DialogFlow.say();
					StringCreator string26 = new StringCreator();
					string26.append("Do you want to try again?");
					state25.setText(string26.toString());
					if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 57, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 59
					Producer.serverPublish("Do you want to try again?");
					iristk.flow.DialogFlow.listen state27 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 57, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 57, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 62
			try {
				count = getCount(515132998) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(515132998);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state28 = new iristk.flow.DialogFlow.say();
							StringCreator string29 = new StringCreator();
							string29.append("Okay, let's try again.");
							state28.setText(string29.toString());
							if (!flowThread.callState(state28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 62, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 64
							Producer.serverPublish("Okay, let's try again.");
							// Line: 65
							Start state30 = new Start();
							flowThread.gotoState(state30, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 65, 25)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 62, 58));
			}
			// Line: 67
			try {
				count = getCount(1651191114) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(1651191114);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state31 = new iristk.flow.DialogFlow.say();
							StringCreator string32 = new StringCreator();
							string32.append("Okay, I was glad to help you.");
							state31.setText(string32.toString());
							if (!flowThread.callState(state31, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 67, 57)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 69
							Producer.serverPublish("Okay, I was glad to help you.");
							// Line: 70
							System.exit(0);
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 67, 57));
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
			// Line: 75
			try {
				count = getCount(212628335) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(212628335);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state33 = new iristk.flow.DialogFlow.say();
						StringCreator string34 = new StringCreator();
						string34.append("I am sorry, I didn't hear anything.");
						state33.setText(string34.toString());
						if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 75, 38)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 77
						Producer.serverPublish("I am sorry, I didn't hear anything.");
						// Line: 78
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 78, 14)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 75, 38));
			}
			// Line: 81
			try {
				count = getCount(2111991224) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:dontknow")) {
						incrCount(2111991224);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 82
							boolean chosen35 = false;
							boolean matching36 = true;
							while (!chosen35 && matching36) {
								int rand37 = random(292938459, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching36 = false;
								if (true) {
									matching36 = true;
									if (rand37 >= 0 && rand37 < 1) {
										chosen35 = true;
										iristk.flow.DialogFlow.say state38 = new iristk.flow.DialogFlow.say();
										StringCreator string39 = new StringCreator();
										string39.append("You could try counting on your fingers.");
										state38.setText(string39.toString());
										if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 82, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching36 = true;
									if (rand37 >= 1 && rand37 < 2) {
										chosen35 = true;
										iristk.flow.DialogFlow.say state40 = new iristk.flow.DialogFlow.say();
										StringCreator string41 = new StringCreator();
										string41.append("Try to see this problem as if you had to sum 5 apples and 5 bananas.");
										state40.setText(string41.toString());
										if (!flowThread.callState(state40, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 82, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 86
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 86, 23)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 81, 64));
			}
			// Line: 89
			try {
				count = getCount(405662939) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(405662939);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state42 = new iristk.flow.DialogFlow.say();
						StringCreator string43 = new StringCreator();
						string43.append("I am sorry, I didn't get that.");
						state42.setText(string43.toString());
						if (!flowThread.callState(state42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 89, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 91
						Producer.serverPublish("I am sorry, I didn't get that.");
						// Line: 92
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 92, 14)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\tutoring\\src\\iristk\\app\\tutoring\\TutoringFlow.xml"), 89, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
