package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class BsNotEnoughCategorizationsRule extends
		AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 28;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var)
			throws RequiredVarException {
		return (in.getName().equals("duration")
				&& "clustering".equals(var.get("BRAINSTORMING_STEP"))
				&& "10".equals(var.get("TOPIC_APPLICATION")) && (getRequiredVar(
					var, "TEMP_CATEGORIZED_IDEA_COUNT", Integer.class) == SensingEventManagerConsts.MIN_CATEGORIZED_IDEA_COUNT) && (getRequiredVar(
					var, "INITAL_RUN", Boolean.class) == false));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		var.put("INITAL_RUN", true);
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 45);
		out.setProperty("headline",
				"Die Meeting-Teilnehmer kategorisieren keine weiteren Ideen.");
		out.setProperty("text", "Was wollen Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Wechseln zur nächsten Phase Clustering Result",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.bsClusteringResult");
		options.put("Eine Nachricht an alle Teilnehmer senden", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.sendMessage");
		options.put("Meeting beenden", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.quit");
		options.put("Abbrechen", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.cancel");
		out.setProperty("options", options);
	}

}
