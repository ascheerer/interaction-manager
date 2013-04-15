package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsUncategorizedIdeasRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 30;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("brainstorming")
				&& "BsSwitchToNextIdeationView".equals(in
						.getProperty("eventType"))
				&& "clustering-result".equals(in.getProperty("viewName"))
				&& "10".equals(var.get("TOPIC_APPLICATION")) && (Integer) var
				.get("CATEGORIZED_IDEA_COUNT") < (Integer) var
				.get("IDEA_COUNT"));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 1);
		out.setProperty("headline",
				"Es wurden noch nicht alle Ideen kategorisiert.");
		out.setProperty("text", "Was wollen Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Wechseln zur vorherigen Phase Clustering zurück",
				"clusteringResult");
		options.put("Eine Nachricht an alle Teilnehmer senden", "sendMessage");
		options.put("Meeting beenden", "quit");
		options.put("Abbrechen", "cancel");
		out.setProperty("options", options);
	}

}
