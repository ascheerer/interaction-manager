package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;

@Service
public class BsUserPriorizedAllIdeasRule extends AbstractSensingEventManagerRule {

	@Override
	public int getIndex() {
		return 27;
	}

	@Override
	protected boolean ruleCondition(Event in, Event out, InstanceVars var) {
		return (in.getName().equals("brainstorming")
				&& "clustering".equals(in.getProperty("viewName")) && "BsUserPriorizedAllIdeas".equals(in.getProperty("eventType")));
	}

	@Override
	protected void ruleBody(Event in, Event out, InstanceVars var) {
		out.setAppType(in.getAppType());
		out.setAppInstanceId(in.getAppInstanceId());
		out.setName("recommendation");
		out.setProperty("eventId", 1);
		out.setProperty("headline",
				"Die Meeting-Teilnehmer haben alle Ideen kategorisiert.");
		out.setProperty("text",
				"Was möchten Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Wechseln zur nächsten Phase Clustering Result", "clusteringResult");
		options.put("Wechseln zur vorherigen Phase Ideen sammeln zurück und lassen Sie neue Ideen erzeugen", "ideation");
		options.put("Eine Nachricht an alle Teilnehmer senden","sendMessage");
		options.put("Meeting beenden","quit");
		options.put("Abbrechen","cancel");
		out.setProperty("options", options);
	}

}
