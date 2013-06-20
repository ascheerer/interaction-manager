package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsNotEnoughCategorizationsRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"duration\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"participantId\":\"506d20b0eb7f3f1e03000002\",\"participantName\":\"user1\"},\"customVars\":{\"id\":1.36553935518945E14,\"eventId\":4.0,\"eventType\":\"participant\",\"senderTime\":\"userJoin\",\"_dateCreated\":\"2013-04-09\",\"_timeCreated\":\"22:29:15\"}}";
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("TEMP_CATEGORIZED_IDEA_COUNT", SensingEventManagerConsts.MIN_CATEGORIZED_IDEA_COUNT);
		givenInstanceVars.put("TOPIC_APPLICATION", "10");
		givenInstanceVars.put("BRAINSTORMING_STEP", "clustering");

		// expected
		Event expectedEvent = new Event();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("recommendation");
		expectedEvent.setProperty("eventId", 1);
		expectedEvent
				.setProperty("headline", "Die Meeting-Teilnehmer kategorisieren keine weiterenIdeen.");
		expectedEvent
				.setProperty(
						"text",
						"Was wollen Sie tun?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Wechseln zur nächsten Phase Clustering Result", "clusteringResult");
		options.put("Eine Nachricht an alle Teilnehmer senden", "sendMessage");
		options.put("Meeting beenden", "quit");
		options.put("Abbrechen", "cancel");
		expectedEvent.setProperty("options", options);

		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);

		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new BsNotEnoughCategorizationsRule();
	}
}
