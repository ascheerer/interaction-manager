package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BSAddIdeaVarRuleTest extends AbstractRuleTest {

	//when((in.customVars.appName == "brainstorming" AND (BRAINSTORMING_STEP = "ideation")) AND (in.name == "BsAddIdea")).setVar(IDEA_COUNT++)

	
	private static final String inEventJson = "{\"name\":\"BsAddIdea\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{},\"customVars\":{\"appName\":\"brainstorming\"}}"; 
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		givenInstanceVars.put("BRAINSTORMING_STEP", "ideation");
		
		// expected 
		Event expectedEvent = new Event();
		
		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.putAll(givenInstanceVars);
		expectedInstanceVars.put("IDEA_COUNT", 1);
		expectedInstanceVars.put("TEMP_IDEA_COUNT", 1);
		
		
		// test a: incr vars not set before
		addTestData(givenEvent, givenInstanceVars, expectedEvent, expectedInstanceVars);

		// test b: incr vars set before
		InstanceVars givenInstanceVars1 = new InstanceVars();
		givenInstanceVars1.putAll(givenInstanceVars);
		givenInstanceVars1.put("IDEA_COUNT", 4);
		givenInstanceVars1.put("TEMP_IDEA_COUNT", 2);
		
		InstanceVars expectedInstanceVars1 = new InstanceVars();
		expectedInstanceVars1.putAll(expectedInstanceVars);
		expectedInstanceVars1.put("IDEA_COUNT", 5);
		expectedInstanceVars1.put("TEMP_IDEA_COUNT", 3);
		addTestData(givenEvent, givenInstanceVars1, expectedEvent, expectedInstanceVars1);
	}

	@Override
	protected Rule getRule() {
		return new BSAddIdeaVarRule();
	}
}
