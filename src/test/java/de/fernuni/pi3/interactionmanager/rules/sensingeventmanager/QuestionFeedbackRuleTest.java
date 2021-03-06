package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;
import de.fernuni.pi3.interactionmanager.rules.sensingeventmanager.QuestionFeedbackRule;

public class QuestionFeedbackRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"topic\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"topicId\":3.0,\"topicTitle\":\"Brainstorming\",\"topicApplication\":\"12\",\"topicDuration\":2700000.0},\"customVars\":{\"id\":1.3642399748109E14,\"eventId\":3.0,\"eventType\":\"topic\",\"senderTime\":\"topicChange\",\"_dateCreated\":\"2013-03-25T20:32:54\",\"_timeCreated\":\"20:32:54\"}}"; 

	@Override
	@Before
	public void setUpTestData() {

		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("MEETING_TYPE", "Planning");

		// expected
		Event expectedEvent = createTestEvent();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("feedback");
		expectedEvent.setProperty("eventId", 21);
		expectedEvent.setProperty("reActivation", true);
		expectedEvent.setProperty("type", "question");
		expectedEvent.setProperty("headline", "Möchten Sie eine Frage stellen?");

		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);

		addTestData(givenEvent, givenInstanceVars, expectedEvent,
				expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new QuestionFeedbackRule();
	}
}
