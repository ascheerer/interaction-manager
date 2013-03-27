package de.fernuni.pi3.interactionmanager.sesingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class MeetingVarsRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 3;
	}

	@Override
	public void processEvent(Event in, Event out, InstanceVars var) {
		if (in.getName().equals("meeting") && in.getProperty("applicationName").equals("MeetingStar™")) {
			var.put("MEETING_TYPE", in.getProperty("meetingType"));
			var.put("MEETING_DURATION", in.getProperty("meetingDuration"));
			var.put("START_TIME", in.getProperty("startTime"));
			var.put("END_TIME", in.getProperty("endTime"));
		}
	}

}
