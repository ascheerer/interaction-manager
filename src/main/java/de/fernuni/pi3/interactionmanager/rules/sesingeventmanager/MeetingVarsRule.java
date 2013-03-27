package de.fernuni.pi3.interactionmanager.rules.sesingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class MeetingVarsRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("meeting"));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.put("MEETING_TYPE", in.getProperty("meetingType"));
		var.put("MEETING_DURATION", in.getProperty("meetingDuration"));
	}

}
