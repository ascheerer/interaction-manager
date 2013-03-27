package de.fernuni.pi3.interactionmanager.rules.sesingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class TimeShortageRedRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 6;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("duration")
				&& (var.get("TOPIC_APPLICATION") != null) && ((Double) in
				.getProperty("timePast") - (Double) var.get("TOPIC_START") >= 0.9 * (Double) var
				.get("TOPIC_DURATION")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {

		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("colorChanger");
		out.setProperty("eventId", 2);
		out.setProperty("type", "red");
	}
}
