package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.RequiredVarException;

@Service
public class BsUncategorizedIdeasRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 30;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) throws RequiredVarException {
		return (in.getName().equals("brainstorming")
				&& "BsSwitchToNextIdeationView".equals(in
						.getProperty("eventType"))
				&& "clustering-result".equals(in.getProperty("viewName"))
				&& "10".equals(var.get("TOPIC_APPLICATION")) && getRequiredVar(
				var, "CATEGORIZED_IDEA_COUNT", Integer.class) < getRequiredVar(
				var, "IDEA_COUNT", Integer.class));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 46);
		out.setProperty("headline",
				"Es wurden noch nicht alle Ideen kategorisiert.");
		out.setProperty("text", "Was wollen Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Wechseln zur vorherigen Phase Clustering zurück",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.bsClusteringResult");
		options.put("Eine Nachricht an alle Teilnehmer senden", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.sendMessage");
		options.put("Meeting beenden", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.quit");
		options.put("Abbrechen", "Meetingstar.util.global.sensingEngine.MagicButtonFunctions.cancel");
		out.setProperty("options", options);
	}

}
