package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class NotEnoughParticipantsRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 11;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var)
			throws RequiredVarException {
		return in.getName().equals("participant")
				&& "Planning".equals(var.get("MEETING_TYPE"))
				&& getRequiredVar(var, "PARTICIPANT_COUNT", Integer.class) < SensingEventManagerConsts.MIN_PARTICIPANT_COUNT
				&& (getRequiredVar(var, "TIME_PAST", Double.class) > 180000);
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 41);
		out.setProperty("headline",
				"Die maximale Anzahl der Teilnehmer wurde unterschritten.");
		out.setProperty("text",
				"Wollen Sie mit dem Meeting fortfahren oder es beenden?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Meeting beenden",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.quit");
		options.put("Fortfahren",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.continue");
		out.setProperty("options", options);
	}

}
