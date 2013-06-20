package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;
import de.fernuni.pi3.interactionmanager.rules.sensingeventmanager.TimeShortageRedRule;

public class TimeShortageRedRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"duration\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"pulseCount\":3.0,\"meetingDuration\":5400000.0,\"timePast\":950000.0,\"timeLeft\":4400000.0},\"customVars\":{\"id\":1.36423500304426E14,\"eventId\":2.0,\"eventType\":\"duration\",\"senderTime\":\"immediate\",\"_dateCreated\":\"2013-03-25T19:10:03\",\"_timeCreated\":\"19:10:03\"}}";

	@Override
	@Before
	public void setUpTestData() {

		// test a: meeting time 790000 = 79% => no event expected
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("TOPIC_APPLICATION", "10");
		givenInstanceVars.put("TOPIC_DURATION", 1000000.0);
		givenInstanceVars.put("TOPIC_START", 160000.0);

		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);

		addTestData(givenEvent, givenInstanceVars, null,
				expectedInstanceVars);

		
		// test b: meeting time 940000 = 94% => no event expected 
		// given
		InstanceVars givenInstanceVars1 = new InstanceVars();
		givenInstanceVars1.putAll(givenInstanceVars);
		givenInstanceVars1.put("TOPIC_START", 10000.0);

		// expected
		Event expectedEvent = createTestEvent();
		expectedEvent.setAppType(givenEvent.getAppType());
		expectedEvent.setAppInstanceId(givenEvent.getAppInstanceId());
		expectedEvent.setName("colorChanger");
		expectedEvent.setProperty("eventId", 31);
		expectedEvent.setProperty("type", "red");
		
		InstanceVars expectedInstanceVars1 = new InstanceVars();
		expectedInstanceVars1.putAll(givenInstanceVars1);
		
		addTestData(givenEvent, givenInstanceVars1, expectedEvent,
				expectedInstanceVars1);
	}

	@Override
	protected Rule getRule() {
		return new TimeShortageRedRule();
	}
}
