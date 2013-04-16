package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class TimeShortageYellowRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 5;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) throws RequiredVarException {
		return (in.getName().equals("duration")
				&& (var.get("TOPIC_APPLICATION") != null)
				&& ((Double) in.getProperty("timePast")
						- getRequiredVar(var, "TOPIC_START", Double.class) >= 0.7 * getRequiredVar(
						var, "TOPIC_DURATION", Double.class)) && ((Double) in
				.getProperty("timePast")
				- getRequiredVar(var, "TOPIC_START", Double.class) < 0.9 * getRequiredVar(
				var, "TOPIC_DURATION", Double.class)));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("colorChanger");
		out.setProperty("eventId", 2);
		out.setProperty("type", "yellow");
	}
}
