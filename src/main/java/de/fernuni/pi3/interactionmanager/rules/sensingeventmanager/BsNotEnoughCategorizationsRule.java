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
					var, "TEMP_CATEGORIZED_IDEA_COUNT", Integer.class) == SensingEventManagerConsts.MIN_CATEGORIZED_IDEA_COUNT));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 1);
		out.setProperty("headline",
				"Die Meeting-Teilnehmer kategorisieren keine weiterenIdeen.");
		out.setProperty("text", "Was wollen Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Wechseln zur nächsten Phase Clustering Result",
				"clusteringResult");
		options.put("Eine Nachricht an alle Teilnehmer senden", "sendMessage");
		options.put("Meeting beenden", "quit");
		options.put("Abbrechen", "cancel");
		out.setProperty("options", options);
	}

}
