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
package iristk.situated;

import java.util.List;

public class Agent extends Body {

	public static final String NOBODY = "nobody";
	public static final String ALL = "all";
	public static final String UNKNOWN = "unknown";

	@RecordField
	public String attending = UNKNOWN;
	
	@RecordField
	public boolean speaking = false;
	
	@RecordField
	public AgentData agentdata;
	
	public Agent(String id) {
		super(id);
	}
	
	public Agent() {
	}
	
	public void setAttending(String target) {
		attending = target;
	}
	
	public void setAttendingAll() {
		attending = ALL;
	}

	public boolean isOnlyAttending(String target) {
		return attending.equals(target);
	}
	
	public boolean isAttending(String target) {
		return isAttendingAll() || attending.equals(target);
	}
	
	public boolean isAttendingAll() {
		return attending.equals(ALL);
	}

	public boolean isAttendingUnknown() {
		return attending.equals(UNKNOWN);
	}
	
	public boolean isAttendingNobody() {
		return attending.equals(NOBODY);
	}
	
	public boolean isSpeaking() {
		return speaking;
	}
	/**
	 * Used to check between the 'nobody' Agent and other Agents.
	 */
	public boolean isNobody() {
		return id.equals(NOBODY);
	}
	
	/**
	 * Used to differentiate between Furhats and human Agents. 
	 * <p> Use isNobody() instead to verify that Agent isn't 'null'
	 * @return
	 */
	public boolean isHuman() {
		return true;
	}

	public static Location getMiddleLocation(List<Agent> users) {
		Location[] locations = new Location[users.size()];
		for (int i = 0; i < users.size(); i++) {
			locations[i] = users.get(i).getHeadLocation();
		}
		return Location.mean(locations);
	}
	
	public void setAgentData(AgentData agentdata)
	{
		this.agentdata = agentdata;
	}
	
	/*
	public Situation getSituation() {
		return situation;
	}
	*/
		
}
