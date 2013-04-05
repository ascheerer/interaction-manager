package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class NotEnoughParticipantsRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 11;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("participant")
				&& ((Integer) var.get("PARTICIPANT_COUNT") < SensingEventManagerConsts.MIN_PARTICIPANT_COUNT)
				&& "Planning".equals(var.get("MEETING_TYPE")) && ((Double) var
				.get("TIME_PAST") > 180000));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 1);
		out.setProperty("headline",
				"Die maximale Anzahl der Teilnehmer wurde unterschritten.");
		out.setProperty("text",
				"Wollen Sie mit dem Meeting fortfahren oder abbrechen?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Fortfahren", "continue");
		options.put("Abbrechen", "cancel");
		out.setProperty("options", options);
	}

}
