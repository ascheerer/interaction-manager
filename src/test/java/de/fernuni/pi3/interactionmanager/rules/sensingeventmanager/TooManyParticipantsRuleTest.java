package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;
import de.fernuni.pi3.interactionmanager.rules.sensingeventmanager.TooManyParticipantsRule;

public class TooManyParticipantsRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"participant\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"feedbackType\":\"question\",\"feedbackAnswer\":\"nein\",\"feedbackAnswerRating\":1.0,\"participantName\":\"user1\"},\"customVars\":{\"id\":1.36437731446243E14,\"eventId\":1.0,\"eventType\":\"feedbackAssistant\",\"senderTime\":\"pulse\",\"_dateCreated\":\"2013-03-27T10:41:54\",\"_timeCreated\":\"10:41:54\"}}";

	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("PARTICIPANT_COUNT", SensingEventManagerConsts.MAX_PARTICIPANT_COUNT + 1);
		givenInstanceVars.put("MEETING_TYPE", "Planning");

		// expected
		Event expectedEvent = createTestEvent();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("recommendation");
		expectedEvent.setProperty("eventId", 40);
		expectedEvent
				.setProperty("headline", "Die maximale Anzahl der Teilnehmer wurde überschritten.");
		expectedEvent
				.setProperty(
						"text",
						"Wollen Sie mit dem Meeting fortfahren oder es beenden?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Abbrechen",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.quit");
		options.put("Fortfahren",
				"Meetingstar.util.global.sensingEngine.MagicButtonFunctions.continue");
		expectedEvent.setProperty("options", options);

		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);

		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new TooManyParticipantsRule();
	}
}
