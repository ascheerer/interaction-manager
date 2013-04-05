package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class ResetInstanceStateRuleTest extends AbstractRuleTest {

	private static final String inEventJson = "{\"name\":\"resetInstanceState\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{},\"customVars\":{}}"; 
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("TESTVAR1", "testval1");
		givenInstanceVars.put("TESTVAR2", "testval2");
		givenInstanceVars.put("TESTVAR2", "testval2");
		
		// expected
		Event expectedEvent = new Event();
		InstanceVars expectedInstanceVars = new InstanceVars();
		
		addTestData(givenEvent, givenInstanceVars, expectedEvent, expectedInstanceVars);
	}

	@Override
	protected Rule getRule() {
		return new ResetInstanceStateRule();
	}

}
