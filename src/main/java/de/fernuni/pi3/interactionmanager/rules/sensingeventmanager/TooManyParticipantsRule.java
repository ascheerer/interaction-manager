package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class TooManyParticipantsRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 10;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var)
			throws RequiredVarException {
		return (in.getName().equals("participant")
				&& "Planning".equals(var.get("MEETING_TYPE")) && (getRequiredVar(
				var, "PARTICIPANT_COUNT", Integer.class) > SensingEventManagerConsts.MAX_PARTICIPANT_COUNT));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 40);
		out.setProperty("headline",
				"Die maximale Anzahl der Teilnehmer wurde überschritten.");
		out.setProperty("text",
				"Wollen Sie mit dem Meeting fortfahren oder abbrechen?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Fortfahren", "continue");
		options.put("Abbrechen", "cancel");
		out.setProperty("options", options);
	}

}
