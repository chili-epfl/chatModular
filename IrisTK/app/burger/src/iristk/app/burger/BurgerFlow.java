package iristk.app.burger;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;

public class BurgerFlow extends iristk.flow.Flow {

	private Record order;

	private void initVariables() {
		order = asRecord(new Record());
	}

	public Record getOrder() {
		return this.order;
	}

	public void setOrder(Record value) {
		this.order = value;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("order")) return this.order;
		return null;
	}


	public BurgerFlow() {
		initVariables();
	}

	@Override
	public State getInitialState() {return new Start();}


	public class Start extends Dialog implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 9
			try {
				EXECUTION: {
					int count = getCount(1347137144) + 1;
					incrCount(1347137144);
					// Line: 10
					if (count == 1) {
						iristk.flow.DialogFlow.say state0 = new iristk.flow.DialogFlow.say();
						StringCreator string1 = new StringCreator();
						string1.append("Welcome");
						state0.setText(string1.toString());
						if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 10, 35)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					iristk.flow.DialogFlow.say state2 = new iristk.flow.DialogFlow.say();
					StringCreator string3 = new StringCreator();
					string3.append("May I please take your order");
					state2.setText(string3.toString());
					if (!flowThread.callState(state2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 9, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state4 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 9, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 9, 18));
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
			// Line: 20
			try {
				count = getCount(1174290147) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:order")) {
						incrCount(1174290147);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 21
							order.adjoin(asRecord(event.get("sem:order")));
							// Line: 22
							CheckOrder state5 = new CheckOrder();
							flowThread.gotoState(state5, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 22, 39)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 20, 66));
			}
			// Line: 24
			try {
				count = getCount(1607460018) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1607460018);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state6 = new iristk.flow.DialogFlow.say();
						StringCreator string7 = new StringCreator();
						string7.append("Sorry, I");
						StringCreator string8 = new StringCreator();
						string8.append("<str cond=\"count > 1\">");
						string8.append("still");
						string8.append("</str>");
						string7.append(string8.toString());
						string7.append("didn't get that");
						state6.setText(string7.toString());
						if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 24, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 26
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 26, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 24, 42));
			}
			// Line: 28
			try {
				count = getCount(1588970020) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(1588970020);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.flow.DialogFlow.say state9 = new iristk.flow.DialogFlow.say();
						StringCreator string10 = new StringCreator();
						string10.append("Sorry, I");
						StringCreator string11 = new StringCreator();
						string11.append("<str cond=\"count > 1\">");
						string11.append("still");
						string11.append("</str>");
						string10.append(string11.toString());
						string10.append("didn't hear anything");
						state9.setText(string10.toString());
						if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 28, 44)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 30
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 30, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 28, 44));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class CheckOrder extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 36
			try {
				EXECUTION: {
					int count = getCount(245565335) + 1;
					incrCount(245565335);
					// Line: 37
					if (!order.has("main")) {
						// Line: 38
						RequestMain state12 = new RequestMain();
						flowThread.gotoState(state12, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 38, 44)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 39
					} else if (!order.has("drink")) {
						// Line: 40
						RequestDrink state13 = new RequestDrink();
						flowThread.gotoState(state13, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 40, 45)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 41
					} else if (eq(order.get("drink:type"), "milkshake") && !order.has("drink:flavor")) {
						// Line: 42
						RequestFlavor state14 = new RequestFlavor();
						flowThread.gotoState(state14, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 42, 46)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 43
					} else if (!order.has("side")) {
						// Line: 44
						RequestSide state15 = new RequestSide();
						flowThread.gotoState(state15, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 44, 44)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 46
					} else {
						// Line: 47
						Done state16 = new Done();
						flowThread.gotoState(state16, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 47, 37)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 36, 18));
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


	private class RequestMain extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 54
			try {
				EXECUTION: {
					int count = getCount(914424520) + 1;
					incrCount(914424520);
					iristk.flow.DialogFlow.say state17 = new iristk.flow.DialogFlow.say();
					StringCreator string18 = new StringCreator();
					string18.append("Do you want a hamburger?");
					state17.setText(string18.toString());
					if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 54, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state19 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state19, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 54, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 54, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 58
			try {
				count = getCount(110718392) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(110718392);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 59
							order.putIfNotNull("main:type", "hamburger");
							// Line: 60
							CheckOrder state20 = new CheckOrder();
							flowThread.gotoState(state20, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 60, 39)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 58, 64));
			}
			// Line: 63
			try {
				count = getCount(1100439041) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(1100439041);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 64
							order.putIfNotNull("main:type", "none");
							// Line: 65
							CheckOrder state21 = new CheckOrder();
							flowThread.gotoState(state21, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 65, 39)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 63, 63));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class RequestDrink extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 71
			try {
				EXECUTION: {
					int count = getCount(32374789) + 1;
					incrCount(32374789);
					iristk.flow.DialogFlow.say state22 = new iristk.flow.DialogFlow.say();
					StringCreator string23 = new StringCreator();
					string23.append("Do you want anything to drink?");
					state22.setText(string23.toString());
					if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 71, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state24 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 71, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 71, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 75
			try {
				count = getCount(1973538135) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(1973538135);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state25 = new iristk.flow.DialogFlow.say();
							StringCreator string26 = new StringCreator();
							string26.append("So what do you want to drink?");
							state25.setText(string26.toString());
							if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 75, 64)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.flow.DialogFlow.listen state27 = new iristk.flow.DialogFlow.listen();
							if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 75, 64)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 75, 64));
			}
			// Line: 79
			try {
				count = getCount(1023487453) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(1023487453);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 80
							order.putIfNotNull("drink:type", "none");
							// Line: 81
							CheckOrder state28 = new CheckOrder();
							flowThread.gotoState(state28, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 81, 39)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 79, 63));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class RequestFlavor extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 87
			try {
				EXECUTION: {
					int count = getCount(1365202186) + 1;
					incrCount(1365202186);
					iristk.flow.DialogFlow.say state29 = new iristk.flow.DialogFlow.say();
					StringCreator string30 = new StringCreator();
					string30.append("What flavor do you want in your milkshake?");
					state29.setText(string30.toString());
					if (!flowThread.callState(state29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 87, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state31 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state31, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 87, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 87, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 91
			try {
				count = getCount(1651191114) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:flavor")) {
						incrCount(1651191114);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 92
							order.putIfNotNull("drink:flavor", event.get("sem:flavor"));
							// Line: 93
							CheckOrder state32 = new CheckOrder();
							flowThread.gotoState(state32, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 93, 39)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 91, 67));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class RequestSide extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 99
			try {
				EXECUTION: {
					int count = getCount(212628335) + 1;
					incrCount(212628335);
					iristk.flow.DialogFlow.say state33 = new iristk.flow.DialogFlow.say();
					StringCreator string34 = new StringCreator();
					string34.append("Do you want anything on the side, such as fries or salad?");
					state33.setText(string34.toString());
					if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 99, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.flow.DialogFlow.listen state35 = new iristk.flow.DialogFlow.listen();
					if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 99, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 99, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 103
			try {
				count = getCount(1579572132) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(1579572132);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.flow.DialogFlow.say state36 = new iristk.flow.DialogFlow.say();
							StringCreator string37 = new StringCreator();
							string37.append("So what do you want on the side?");
							state36.setText(string37.toString());
							if (!flowThread.callState(state36, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 103, 64)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.flow.DialogFlow.listen state38 = new iristk.flow.DialogFlow.listen();
							if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 103, 64)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 103, 64));
			}
			// Line: 107
			try {
				count = getCount(305808283) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(305808283);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 108
							order.putIfNotNull("side:type", "none");
							// Line: 109
							CheckOrder state39 = new CheckOrder();
							flowThread.gotoState(state39, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 109, 39)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 107, 63));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Done extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 115
			try {
				EXECUTION: {
					int count = getCount(1993134103) + 1;
					incrCount(1993134103);
					iristk.flow.DialogFlow.say state40 = new iristk.flow.DialogFlow.say();
					StringCreator string41 = new StringCreator();
					string41.append("Okay, thanks for your order");
					state40.setText(string41.toString());
					if (!flowThread.callState(state40, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 115, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 117
					StringCreator string42 = new StringCreator();
					// Line: 117
					string42.append(order.toStringIndent());
					log(string42.toString());
					// Line: 118
					System.exit(0);
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\Meret\\IrisTK\\app\\burger\\src\\iristk\\app\\burger\\BurgerFlow.xml"), 115, 18));
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


}
