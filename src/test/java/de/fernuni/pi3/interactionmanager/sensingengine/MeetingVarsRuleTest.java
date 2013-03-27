package de.fernuni.pi3.interactionmanager.sensingengine;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.Rule;
import de.fernuni.pi3.interactionmanager.sesingeventmanager.MeetingVarsRule;

public class MeetingVarsRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"meeting\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"meetingId\":\"50924677bbcdaaa713000001\",\"meetingTitle\":\"test\",\"meetingType\":\"Informational\",\"meetingDuration\":5400000.0,\"startTime\":4.35E7,\"endTime\":4.89E7,\"applicationName\":\"MeetingStar™\",\"participantsOptional\":\"506d20b0eb7f3f1e03000001\",\"participantsRequired\":\"506d20b0eb7f3f1e03000003,506d20b0eb7f3f1e03000002,506d20b0eb7f3f1e03000004\"},\"customVars\":{\"id\":1.36423500298577E14,\"eventId\":1.0,\"eventType\":\"meeting\",\"senderTime\":\"immediate\",\"_dateCreated\":\"2013-03-25T19:10:02\",\"_timeCreated\":\"19:10:02\"}}";	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		
		// expected
		Event expectedEvent = new Event();
		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.put("MEETING_DURATION", 5400000.0);
		expectedInstanceVars.put("MEETING_TYPE", "Informational");
		expectedInstanceVars.put("START_TIME", 4.35E7);
		expectedInstanceVars.put("END_TIME", 4.89E7);
		
		addTestData(givenEvent, givenInstanceVars, expectedEvent, expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new MeetingVarsRule();
	}
}
