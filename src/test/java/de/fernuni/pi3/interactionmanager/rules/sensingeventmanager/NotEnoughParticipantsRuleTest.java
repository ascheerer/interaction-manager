package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;
import de.fernuni.pi3.interactionmanager.rules.sensingeventmanager.NotEnoughParticipantsRule;

public class NotEnoughParticipantsRuleTest extends AbstractRuleTest {

	// private static final String inEventJson = "{\"name\":\"participant\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"feedbackType\":\"question\",\"feedbackAnswer\":\"nein\",\"feedbackAnswerRating\":1.0,\"participantName\":\"user1\"},\"customVars\":{\"id\":1.36437731446243E14,\"eventId\":1.0,\"eventType\":\"feedbackAssistant\",\"senderTime\":\"pulse\",\"_dateCreated\":\"2013-03-27T10:41:54\",\"_timeCreated\":\"10:41:54\"}}";
	private static final String inEventJson = "{\"name\":\"participant\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"participantId\":\"506d20b0eb7f3f1e03000002\",\"participantName\":\"user1\"},\"customVars\":{\"id\":1.36553935518945E14,\"eventId\":4.0,\"eventType\":\"participant\",\"senderTime\":\"userJoin\",\"_dateCreated\":\"2013-04-09\",\"_timeCreated\":\"22:29:15\"}}";
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("PARTICIPANT_COUNT", SensingEventManagerConsts.MIN_PARTICIPANT_COUNT - 1);
		givenInstanceVars.put("MEETING_TYPE", "Planning");
		givenInstanceVars.put("TIME_PAST", 200000.0);

		// expected
		Event expectedEvent = createTestEvent();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("recommendation");
		expectedEvent.setProperty("eventId", 41);
		expectedEvent
				.setProperty("headline", "Die maximale Anzahl der Teilnehmer wurde unterschritten.");
		expectedEvent
				.setProperty(
						"text",
						"Wollen Sie mit dem Meeting fortfahren oder es beenden?");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("Meeting beenden",
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
		return new NotEnoughParticipantsRule();
	}
}
