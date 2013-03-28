package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.Rule;
import de.fernuni.pi3.interactionmanager.rules.sensingeventmanager.DurationVarsRule;

public class DurationVarsRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"duration\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"pulseCount\":3.0,\"meetingDuration\":5400000.0,\"timePast\":1000000.0,\"timeLeft\":4400000.0},\"customVars\":{\"id\":1.36423500304426E14,\"eventId\":2.0,\"eventType\":\"duration\",\"senderTime\":\"immediate\",\"_dateCreated\":\"2013-03-25T19:10:03\",\"_timeCreated\":\"19:10:03\"}}"; 
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		
		// expected
		Event expectedEvent = new Event();
		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.put("TIME_PAST", 1000000.0);
		expectedInstanceVars.put("TIME_LEFT", 4400000.0);
		
		addTestData(givenEvent, givenInstanceVars, expectedEvent, expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new DurationVarsRule();
	}

}
