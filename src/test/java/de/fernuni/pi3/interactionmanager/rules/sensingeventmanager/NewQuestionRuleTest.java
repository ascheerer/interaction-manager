package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import java.util.HashMap;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.Rule;
import de.fernuni.pi3.interactionmanager.rules.sesingeventmanager.NewQuestionRule;

public class NewQuestionRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"feedbackAssistant\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"feedbackType\":\"question\",\"feedbackAnswer\":\"nein\",\"feedbackAnswerRating\":1.0,\"participantName\":\"user1\"},\"customVars\":{\"id\":1.36437731446243E14,\"eventId\":1.0,\"eventType\":\"feedbackAssistant\",\"senderTime\":\"pulse\",\"_dateCreated\":\"2013-03-27T10:41:54\",\"_timeCreated\":\"10:41:54\"}}"; 
	
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
		expectedEvent.setName("recommendation");
		expectedEvent.setProperty("eventId",1);
		expectedEvent.setProperty("headline","Neue eingehende Frage von user1");
		expectedEvent.setProperty("text", "Bitte rufen Sie user1 auf und bitten Sie den Teilnehmer, die Frage zu stellen.");
		HashMap<String,String> options = new HashMap<String,String>();
		options.put("Fortfahren","continue");
		expectedEvent.setProperty("options", options);
		
		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.put("QUESTION_COUNT", 1);
		
		// test a: TIME_PAST not set before
		addTestData(givenEvent, givenInstanceVars, expectedEvent, expectedInstanceVars);

		// test b: TIME_PAST set before
		InstanceVars givenInstanceVars1 = new InstanceVars();
		givenInstanceVars1.put("QUESTION_COUNT", 3);
		
		InstanceVars expectedInstanceVars1 = new InstanceVars();
		expectedInstanceVars1.putAll(expectedInstanceVars);
		expectedInstanceVars1.put("QUESTION_COUNT", 4);
		addTestData(givenEvent, givenInstanceVars1, expectedEvent, expectedInstanceVars1);
	}

	@Override
	protected Rule getRule() {
		return new NewQuestionRule();
	}
}
