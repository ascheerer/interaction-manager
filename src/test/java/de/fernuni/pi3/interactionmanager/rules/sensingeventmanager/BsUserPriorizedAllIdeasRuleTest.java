package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsUserPriorizedAllIdeasRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"brainstorming\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"viewName\":\"clustering\",\"eventType\":\"BsUserPriorizedAllIdeas\"},\"customVars\":{}}";
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("NOT_MODIFIED", 1);
		

		// expected
		Event expectedEvent = new Event();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("recommendation");
		expectedEvent.setProperty("eventId", 1);
		expectedEvent
				.setProperty("headline", "Die Meeting-Teilnehmer haben alle Ideen kategorisiert.");
		expectedEvent
				.setProperty(
						"text",
						"Was möchten Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Wechseln zur nächsten Phase Clustering Result", "clusteringResult");
		options.put("Wechseln zur vorherigen Phase Ideen sammeln zurück und lassen Sie neue Ideen erzeugen", "ideation");
		options.put("Eine Nachricht an alle Teilnehmer senden","sendMessage");
		options.put("Meeting beenden","quit");
		options.put("Abbrechen","cancel");
		expectedEvent.setProperty("options", options);

		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);

		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new BsUserPriorizedAllIdeasRule();
	}
}
