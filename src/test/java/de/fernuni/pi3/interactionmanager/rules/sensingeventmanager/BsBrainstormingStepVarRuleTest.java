package de.fernuni.pi3.interactionmanager.rules.sensingeventmanager;

import org.junit.Before;

import de.fernuni.pi3.interactionmanager.Event;
import de.fernuni.pi3.interactionmanager.InstanceVars;
import de.fernuni.pi3.interactionmanager.rules.AbstractRuleTest;
import de.fernuni.pi3.interactionmanager.rules.Rule;

public class BsBrainstormingStepVarRuleTest extends AbstractRuleTest {

	//when((in.customVars.appName == "brainstorming" AND (BRAINSTORMING_STEP = "ideation")) AND (in.name == "BsAddIdea")).setVar(IDEA_COUNT++)

	
	private static final String inEventJson = "{\"name\":\"brainstorming\",\"appType\":\"SensingEventManager\",\"appInstanceId\":\"50924677bbcdaaa713000001\",\"properties\":{\"eventType\":\"BsSwitchToNextIdeationView\",\"viewName\":\"myTestBsStep\"},\"customVars\":{}}"; 
	
	@Override
	@Before
	public void setUpTestData() {
		// given
		Event givenEvent = Event.fromJson(inEventJson);
		InstanceVars givenInstanceVars = new InstanceVars();
		
		// expected 
		Event expectedEvent = new Event();
		
		InstanceVars expectedInstanceVars = new InstanceVars();
		expectedInstanceVars.put("BRAINSTORMING_STEP", "myTestBsStep");
		
		addTestData(givenEvent, givenInstanceVars, expectedEvent, expectedInstanceVars);

	}

	@Override
	protected Rule getRule() {
		return new BsBrainstormingStepVarRule();
	}
}
