package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;
import de.fernuni.pi3.interactionmanager.rules.sensingeventmanager.TooManyQuestionsRule;

public class TooManyQuestionsRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"feedbackAssistant\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"feedbackType\":\"question\",\"feedbackAnswer\":\"nein\",\"feedbackAnswerRating\":1.0,\"participantName\":\"user1\"},\"customVars\":{\"id\":1.36437731446243E14,\"eventId\":1.0,\"eventType\":\"feedbackAssistant\",\"senderTime\":\"pulse\",\"_dateCreated\":\"2013-03-27T10:41:54\",\"_timeCreated\":\"10:41:54\"}}"; 

	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("QUESTION_COUNT", 11);
		givenInstanceVars.put("MEETING_TYPE", "Planning");
		
		// expected
		Event expectedEvent = new Event();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("recommendation");
		expectedEvent.setProperty("eventId", 1);
		expectedEvent
				.setProperty("headline", "Die maximale Anzahl der gestellten Fragen wurde überschritten.");
		expectedEvent
				.setProperty(
						"text",
						"Wollen Sie den Wert zurücksetzen und fortfahren?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Fortfahren", "resetCount");
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
		return new TooManyQuestionsRule();
	}
}
