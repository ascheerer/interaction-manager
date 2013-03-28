package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.Rule;
import de.fernuni.pi3.interactionmanager.rules.sensingeventmanager.TopicVarsAndResetFeedbackRule;

public class TopicVarsAndResetFeedbackRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"topic\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"topicId\":1.0,\"topicTitle\":\"Einleitung\",\"topicApplication\":\"12\",\"topicDuration\":900000.0},\"customVars\":{\"id\":1.36423500328918E14,\"eventId\":3.0,\"eventType\":\"topic\",\"senderTime\":\"topicChange\",\"_dateCreated\":\"2013-03-25T19:10:03\",\"_timeCreated\":\"19:10:03\"}}"; 
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		
		// expected 
		Event expectedEvent = new Event();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("feedback");
		expectedEvent.setProperty("eventId",3);
		expectedEvent.setProperty("type","reset");
		
		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.put("TOPIC_APPLICATION", "12");
		expectedInstanceVars.put("TOPIC_DURATION", 900000.0);
		expectedInstanceVars.put("TOPIC_START", 0.0);
		expectedInstanceVars.put("QUESTION_COUNT", 0);
		
		// test a: TIME_PAST not set before
		addTestData(givenEvent, givenInstanceVars, expectedEvent, expectedInstanceVars);

		// test b: TIME_PAST set before
		InstanceVars givenInstanceVars1 = new InstanceVars();
		givenInstanceVars1.put("TIME_PAST", 10.0);
		
		InstanceVars expectedInstanceVars1 = new InstanceVars();
		expectedInstanceVars1.putAll(expectedInstanceVars);
		expectedInstanceVars1.put("TOPIC_START", 10.0);
		expectedInstanceVars1.put("TIME_PAST", 10.0);
		addTestData(givenEvent, givenInstanceVars1, expectedEvent, expectedInstanceVars1);
	}

	@Override
	protected Rule getRule() {
		return new TopicVarsAndResetFeedbackRule();
	}
}
