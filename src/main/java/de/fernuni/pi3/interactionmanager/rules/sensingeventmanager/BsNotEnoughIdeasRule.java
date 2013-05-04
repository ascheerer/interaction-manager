package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class BsNotEnoughIdeasRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 21;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var)
			throws RequiredVarException {
		return (in.getName().equals("duration")
				&& "10".equals(var.get("TOPIC_APPLICATION"))
				&& "ideation".equals(var.get("BRAINSTORMING_STEP")) && (getRequiredVar(
					var, "INITAL_RUN", Boolean.class) == false));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.put("INITAL_RUN", true);
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 43);
		out.setProperty("headline",
				"Die Meeting-Teilnehmer generieren keine neuen Ideen.");
		out.setProperty("text", "Was wollen Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Wechseln zur nächsten Phase Clustering", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.bsClustering");
		options.put("Eine Nachricht an alle Teilnehmer senden", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.sendMessage");
		options.put("Meeting beenden", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.quit");
		options.put("Abbrechen", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.cancel");
		out.setProperty("options", options);
	}

}
